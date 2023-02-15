package com.manoj.serviceimp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.manoj.entity.Book;
import com.manoj.entity.IssueBook;
import com.manoj.entity.Student;
import com.manoj.exception.CustomResourceNotFoundException;
import com.manoj.exception.ForeignKeyConstraintException;
import com.manoj.exception.ResourceDuplicateException;
import com.manoj.mapper.IssueBookMapper;
import com.manoj.mapper.StudentMapper;
import com.manoj.payload.IssueBookDto;
import com.manoj.payload.StudentDto;
import com.manoj.repository.BookRepo;
import com.manoj.repository.IssueBookRepo;
import com.manoj.repository.StudentRepo;
import com.manoj.service.StudentService;

@Service
public class StudentServiceImp implements StudentService {

	private StudentRepo studentRepo;
	private StudentMapper studentMapper;
	private BookRepo bookRepo;
	private IssueBookRepo issueBookRepo;
	private IssueBookMapper issueBookMapper;
	private String uploadImagesDir;

	public StudentServiceImp(StudentRepo studentRepo, StudentMapper studentMapper, BookRepo bookRepo,
			IssueBookRepo issueBookRepo, IssueBookMapper issueBookMapper,
			@Value("${project.upload.images}") String uploadImagesDir) {
		super();
		this.studentRepo = studentRepo;
		this.studentMapper = studentMapper;
		this.bookRepo = bookRepo;
		this.issueBookRepo = issueBookRepo;
		this.issueBookMapper = issueBookMapper;
		this.uploadImagesDir = uploadImagesDir;
	}

	@Override
	public List<StudentDto> getAllStudents(Integer pageSize, Integer pageNumber) {

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Student> pageStudents = studentRepo.findAll(p);

		List<StudentDto> students = studentMapper.studentEntityListToStudentDtoList(pageStudents.getContent());

		if (students.isEmpty())
			throw new CustomResourceNotFoundException("students list is empty!");
		return students;
	}

	@Override
	public StudentDto getStudent(Long id) {
		Student studentEnt = studentRepo.findById(id)
				.orElseThrow(() -> new CustomResourceNotFoundException("student with id " + id + " does't exist!"));
		return studentMapper.studentEntityToStudentDto(studentEnt);
	}

	@Override
	public StudentDto saveStudent(StudentDto s) {
		Optional<Student> studentExist = studentRepo.findByEmail(s.getEmail());
		if (studentExist.isPresent()) {
			throw new ResourceDuplicateException("student with " + s.getEmail() + " already exist!");
		}

		Student newStudent = new Student();
		newStudent.setStream(s.getStream());
		newStudent.setName(s.getName());
		newStudent.setGender(s.getGender());
		newStudent.setDob(s.getDob());
		newStudent.setContact(s.getContact());
		newStudent.setEmail(s.getEmail());

		return studentMapper.studentEntityToStudentDto(studentRepo.save(newStudent));
	}

	@Override
	public void deleteStudent(Long studentId) throws IOException {
		Student savedStudent = studentRepo.findById(studentId).orElseThrow(
				() -> new CustomResourceNotFoundException("student with id " + studentId + " does't exist!"));

		List<IssueBook> issuedBooks = issueBookRepo.findByStudent(savedStudent);

		if (!issuedBooks.isEmpty())
			throw new ForeignKeyConstraintException(
					"can't delete the student with id " + studentId + "! student must return the issued books first!");

		// check
		System.out.println("uploadImagesDir: " + uploadImagesDir);

		if (savedStudent.getImage() != null) {
//			Files.delete(Paths.get(uploadImagesDir + File.separator + savedStudent.getImage()));
			FileUtils.deleteQuietly(new File(uploadImagesDir + File.separator + savedStudent.getImage()));
		}

		studentRepo.delete(savedStudent);
	}

	@Override
	public StudentDto updateStudent(StudentDto studentDto, Long id) {
		Student savedStudent = studentRepo.findById(id)
				.orElseThrow(() -> new CustomResourceNotFoundException("student with id " + id + " does't exist!"));

		savedStudent.setStream(studentDto.getStream());
		savedStudent.setName(studentDto.getName());
		savedStudent.setGender(studentDto.getGender());
		savedStudent.setDob(studentDto.getDob());
		savedStudent.setContact(studentDto.getContact());

		return studentMapper.studentEntityToStudentDto(studentRepo.save(savedStudent));
	}

	@Override
	public IssueBookDto issueBook(Long studentId, Long bookId) {
		Student s = studentRepo.findById(studentId).orElseThrow(
				() -> new CustomResourceNotFoundException("student with id " + studentId + " does't exist!"));
		Book b = bookRepo.findById(bookId)
				.orElseThrow(() -> new CustomResourceNotFoundException("book with id " + bookId + " does't exist!"));

		Optional<IssueBook> issueBook = issueBookRepo.findByBook(b);

		if (issueBook.isPresent())
			throw new ResourceDuplicateException("book with id " + issueBook.get().getBook().getId()
					+ " already issued to student with id " + issueBook.get().getStudent().getId() + "!");

		IssueBook issueBookEntity = new IssueBook();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate currentDate = LocalDate.now();

		issueBookEntity.setStudent(s);
		issueBookEntity.setBook(b);
		issueBookEntity.setIssueDate(dtf.format(currentDate));
		issueBookEntity.setExpiringDate(dtf.format(currentDate.plusDays(5)));
		issueBookEntity.setFine(0);

		IssueBookDto issueBookDto = issueBookMapper.issueBookEntityToIssueBookDto(issueBookRepo.save(issueBookEntity));

		// update reserved property of savedBook
		b.setReserved(true);
		bookRepo.save(b);

		return issueBookDto;
	}

	@Override
	public void returnIssuedBook(Long studentId, Long bookId) {
		Student savedStudent = studentRepo.findById(studentId).orElseThrow(
				() -> new CustomResourceNotFoundException("student with id " + studentId + " does't exist!"));

		Book savedBook = bookRepo.findById(bookId)
				.orElseThrow(() -> new CustomResourceNotFoundException("book with id " + bookId + " does't exist!"));

		issueBookRepo.findByBook(savedBook).orElseThrow(
				() -> new CustomResourceNotFoundException("book with id " + bookId + " is not issued to any student!"));

		List<IssueBook> issuedBooks = issueBookRepo.findByStudent(savedStudent);

		if (issuedBooks.isEmpty())
			throw new CustomResourceNotFoundException(
					"student with id " + savedStudent.getId() + " has not issued any book!");

		IssueBook issuedBook = null;

		for (IssueBook issueBook : issuedBooks) {
			if (savedBook.getId() == issueBook.getBook().getId())
				issuedBook = issueBook;
		}

		if (issuedBook != null) {
			issueBookRepo.delete(issuedBook);
			// update reserved property of savedBook
			savedBook.setReserved(false);
			bookRepo.save(savedBook);
		} else {
			throw new CustomResourceNotFoundException("book with id " + bookId + " is not issued to you!");
		}
	}

	@Override
	public List<IssueBookDto> getAllIssueBooks(Long studentId, Integer pageSize, Integer pageNumber) {
		Student savedStudent = studentRepo.findById(studentId).orElseThrow(
				() -> new CustomResourceNotFoundException("student with id " + studentId + " does't exist!"));

		List<IssueBook> issuedBooks = issueBookRepo.findByStudent(savedStudent);

		if (issuedBooks.isEmpty())
			throw new CustomResourceNotFoundException(
					"student with id " + savedStudent.getId() + " has not issued any book!");

		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<IssueBook> pageIssuedBooks = issueBookRepo.customFindByStudent(savedStudent, p);

		issuedBooks = pageIssuedBooks.getContent();

		if (issuedBooks.isEmpty()) {
			throw new CustomResourceNotFoundException("issued books list is empty!");
		}

		return issueBookMapper.issueBookEntityListToIssueBookDtoList(issuedBooks);
	}

	@Override
	public String uploadImage(Long studentId, MultipartFile studentImage) throws IllegalStateException, IOException {
		Student savedStudent = studentRepo.findById(studentId).orElseThrow(
				() -> new CustomResourceNotFoundException("student with id " + studentId + " does't exist!"));

		String studentImageName = studentImage.getOriginalFilename();
		if (savedStudent.getImage() != null) {
			studentImageName = savedStudent.getImage();
		} else {
			studentImageName = UUID.randomUUID() + studentImageName.substring(studentImageName.lastIndexOf("."));
		}

		if (!Files.exists(Paths.get(uploadImagesDir))) {
			Files.createDirectories(Paths.get(uploadImagesDir));
		}

		FileUtils.copyInputStreamToFile(studentImage.getInputStream(),
				new File(uploadImagesDir + File.separator + studentImageName));

		savedStudent.setImage(studentImageName);
		String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads")
				.path("/" + studentImageName).toUriString();
		savedStudent.setImageUrl(imageUrl);
		studentRepo.save(savedStudent);

		return imageUrl;
	}

}
