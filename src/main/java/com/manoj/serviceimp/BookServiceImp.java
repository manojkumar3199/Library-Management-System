package com.manoj.serviceimp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import com.manoj.entity.Category;
import com.manoj.entity.IssueBook;
import com.manoj.exception.CustomResourceNotFoundException;
import com.manoj.exception.ForeignKeyConstraintException;
import com.manoj.exception.ResourceDuplicateException;
import com.manoj.mapper.BookMapper;
import com.manoj.mapper.IssueBookMapper;
import com.manoj.payload.BookDto;
import com.manoj.payload.IssueBookDto;
import com.manoj.repository.BookRepo;
import com.manoj.repository.CategoryRepo;
import com.manoj.repository.IssueBookRepo;
import com.manoj.service.BookService;

@Service
public class BookServiceImp implements BookService {

	private BookRepo bookRepo;
	private BookMapper bookMapper;
	private CategoryRepo categoryRepo;
	private IssueBookRepo issueBookRepo;
	private IssueBookMapper issueBookMapper;
	private String uploadImagesDir;

	public BookServiceImp(BookRepo bookRepo, BookMapper bookMapper, CategoryRepo categoryRepo,
			IssueBookRepo issueBookRepo, IssueBookMapper issueBookMapper,
			@Value("${project.upload.images}") String uploadImagesDir) {
		super();
		this.bookRepo = bookRepo;
		this.bookMapper = bookMapper;
		this.categoryRepo = categoryRepo;
		this.issueBookRepo = issueBookRepo;
		this.issueBookMapper = issueBookMapper;
		this.uploadImagesDir = uploadImagesDir;
	}

	@Override
	public List<BookDto> getAllBooks(Integer pageSize, Integer pageNumber) {
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Book> pageBooks = bookRepo.findAll(p);

		List<Book> booksEntity = pageBooks.getContent();
		if (booksEntity.isEmpty())
			throw new CustomResourceNotFoundException("books list is empty!");

		return bookMapper.bookEntityListToBookDtoList(booksEntity);
	}

	@Override
	public BookDto getBook(Long id) {
		Book bookEntity = bookRepo.findById(id)
				.orElseThrow(() -> new CustomResourceNotFoundException("book with id " + id + " does't exist!"));

		return bookMapper.bookEntityToBookDto(bookEntity);
	}

	@Override
	public BookDto saveBook(BookDto b, Long categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(
				() -> new CustomResourceNotFoundException("category with id " + categoryId + " does't exist!"));

		Optional<Book> bookExist = bookRepo.findByTitleIgnoreCase(b.getTitle());
		if (bookExist.isPresent())
			throw new ResourceDuplicateException(b.getTitle() + " already exist!");

		Book newBook = bookMapper.bookDtoToBookEntity(b);
		newBook.setCategory(category);
		newBook.setTitle(b.getTitle());
		newBook.setAuthor(b.getAuthor());
		newBook.setDescription(b.getDescription());

		return bookMapper.bookEntityToBookDto(bookRepo.save(newBook));
	}

	@Override
	public BookDto updateBook(BookDto b, Long bookId, Long categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(
				() -> new CustomResourceNotFoundException("category with id " + categoryId + " does't exist!"));

		Book savedBook = bookRepo.findById(bookId)
				.orElseThrow(() -> new CustomResourceNotFoundException("book with id " + bookId + " does't exist!"));

		Optional<Book> bookExist = bookRepo.findByTitleIgnoreCaseAndIdNot(b.getTitle(), bookId);
		if (bookExist.isPresent())
			throw new ResourceDuplicateException(b.getTitle() + " already exist!");

		savedBook.setTitle(b.getTitle());
		savedBook.setCategory(category);
		savedBook.setAuthor(b.getAuthor());
		savedBook.setDescription(b.getDescription());

		return bookMapper.bookEntityToBookDto(bookRepo.save(savedBook));
	}

	@Override
	public void deleteBook(Long id) throws IOException {
		Book savedBook = bookRepo.findById(id)
				.orElseThrow(() -> new CustomResourceNotFoundException("book with id " + id + " does't exist!"));

		Optional<IssueBook> issueBook = issueBookRepo.findByBook(savedBook);

		if (issueBook.isPresent()) {
			throw new ForeignKeyConstraintException("can't delete this book!",
					"this book is issued to student with id " + issueBook.get().getStudent().getId());
		}

		if (savedBook.getImage() != null) {
//			Files.delete(Paths.get(uploadImagesDir + File.separator + savedBook.getImage()));
			FileUtils.deleteQuietly(new File(uploadImagesDir + File.separator + savedBook.getImage()));
		}

		bookRepo.delete(savedBook);
	}

	@Override
	public IssueBookDto getIssueBook(Long bookId) {
		Book b = bookRepo.findById(bookId)
				.orElseThrow(() -> new CustomResourceNotFoundException("book with id " + bookId + " does't exist!"));

		return issueBookMapper.issueBookEntityToIssueBookDto(
				issueBookRepo.findByBook(b).orElseThrow(() -> new CustomResourceNotFoundException(
						"book with id " + bookId + " is not issued to any student!")));
	}

	@Override
	public String uploadImage(Long bookId, MultipartFile bookImage) throws IllegalStateException, IOException {
		Book savedBook = bookRepo.findById(bookId)
				.orElseThrow(() -> new CustomResourceNotFoundException("book with id " + bookId + " does't exist!"));

		String bookImageName = bookImage.getOriginalFilename();
		if (savedBook.getImage() != null) {
			bookImageName = savedBook.getImage();
		} else {
			bookImageName = UUID.randomUUID() + bookImageName.substring(bookImageName.lastIndexOf("."));
		}

		if (!Files.exists(Paths.get(uploadImagesDir))) {
			Files.createDirectories(Paths.get(uploadImagesDir));
		}

		FileUtils.copyInputStreamToFile(bookImage.getInputStream(),
				new File(uploadImagesDir + File.separator + bookImageName));

		savedBook.setImage(bookImageName);
		String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads")
				.path("/" + bookImageName).toUriString();
		savedBook.setImageUrl(imageUrl);
		bookRepo.save(savedBook);

		return imageUrl;
	}

}