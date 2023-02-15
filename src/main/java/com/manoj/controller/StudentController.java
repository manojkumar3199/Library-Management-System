package com.manoj.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.manoj.payload.IssueBookDto;
import com.manoj.payload.StudentDto;
import com.manoj.service.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {

	private StudentService service;

	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<StudentDto> getAllStudents(
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber) {
		return service.getAllStudents(pageSize, pageNumber);
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public StudentDto getStudent(@PathVariable("id") Long id) {
		return service.getStudent(id);
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public StudentDto saveStudent(@Valid @RequestBody StudentDto s) {
		return service.saveStudent(s);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteStudent(@PathVariable("id") Long id) throws IOException {
		service.deleteStudent(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public StudentDto updateStudent(@Valid @RequestBody StudentDto s, @PathVariable("id") Long id) {
		return service.updateStudent(s, id);
	}

	@GetMapping("/{studentId}/issuebook/{bookId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MappingJacksonValue issueBook(@PathVariable("studentId") Long studentId,
			@PathVariable("bookId") Long bookId) {
		IssueBookDto issueBook = service.issueBook(studentId, bookId);
		// applying filter
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("IssueBookDto",
						SimpleBeanPropertyFilter.filterOutAllExcept("id", "book", "issueDate", "expiringDate", "fine"))
				.addFilter("BookDto", SimpleBeanPropertyFilter.filterOutAllExcept("id", "title", "author",
						"description", "imageUrl", "category"));
		MappingJacksonValue mapping = new MappingJacksonValue(issueBook);
		mapping.setFilters(filters);
		return mapping;
	}

	@GetMapping("/{studentId}/returnbook/{bookId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void returnIssuedBook(@PathVariable("studentId") Long studentId, @PathVariable("bookId") Long bookId) {
		service.returnIssuedBook(studentId, bookId);
	}

	@GetMapping("/{studentId}/issuebook")
	@ResponseStatus(code = HttpStatus.OK)
	public MappingJacksonValue getAllIssueBooks(@PathVariable("studentId") Long studentId,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber) {
		List<IssueBookDto> issuedBooks = service.getAllIssueBooks(studentId, pageSize, pageNumber);
		// applying filter
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("IssueBookDto",
						SimpleBeanPropertyFilter.filterOutAllExcept("id", "book", "issueDate", "expiringDate", "fine"))
				.addFilter("BookDto", SimpleBeanPropertyFilter.filterOutAllExcept("id", "title", "author",
						"description", "imageUrl", "category"));
		MappingJacksonValue mapping = new MappingJacksonValue(issuedBooks);
		mapping.setFilters(filters);
		return mapping;
	}

	@PostMapping("/{studentId}/uploadimage")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Map<String, Object> uploadImage(@PathVariable("studentId") Long studentId,
			@RequestParam(name = "studentImage", required = true) MultipartFile studentImage)
			throws IllegalStateException, IOException {
		String imageUrl = service.uploadImage(studentId, studentImage);
		HashMap<String, Object> res = new HashMap<>();
		res.put("imageUrl", imageUrl);
		return res;
	}

}