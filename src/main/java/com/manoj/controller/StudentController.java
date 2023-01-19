package com.manoj.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
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
	public List<StudentDto> getAllStudents(
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber) {
		return service.getAllStudents(pageSize, pageNumber);
	}

	@GetMapping("/{id}")
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
	public StudentDto updateStudent(@Valid @RequestBody StudentDto s, @PathVariable("id") Long id) {
		return service.updateStudent(s, id);
	}

	@GetMapping("/{studentId}/issuebook/{bookId}")
	public IssueBookDto issueBook(@PathVariable("studentId") Long studentId, @PathVariable("bookId") Long bookId) {
		return service.issueBook(studentId, bookId);
	}

	@GetMapping("/{studentId}/returnbook/{bookId}")
	public void returnIssuedBook(@PathVariable("studentId") Long studentId, @PathVariable("bookId") Long bookId) {
		service.returnIssuedBook(studentId, bookId);
	}

	@GetMapping("/{studentId}/issuebook")
	public List<IssueBookDto> getAllIssueBooks(@PathVariable("studentId") Long studentId,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber) {
		return service.getAllIssueBooks(studentId, pageSize, pageNumber);
	}

	@PostMapping("/{studentId}/uploadimage")
	public String uploadImage(@PathVariable("studentId") Long studentId,
			@RequestParam(name = "studentImage", required = true) MultipartFile studentImage)
			throws IllegalStateException, IOException {
		return service.uploadImage(studentId, studentImage);
	}

}