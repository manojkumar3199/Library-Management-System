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

import com.manoj.payload.BookDto;
import com.manoj.payload.IssueBookDto;
import com.manoj.service.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

	private BookService service;

	@GetMapping("")
	public List<BookDto> getAllBooks(
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber) {
		return service.getAllBooks(pageSize, pageNumber);
	}

	@GetMapping("/{id}")
	public BookDto getBook(@PathVariable("id") Long id) {
		return service.getBook(id);
	}

	@PostMapping("/category/{categoryId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookDto saveBook(@Valid @RequestBody BookDto b, @PathVariable("categoryId") Long categoryId) {
		return service.saveBook(b, categoryId);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable("id") Long id) throws IOException {
		service.deleteBook(id);
	}

	@PutMapping("/{bookId}/category/{categoryId}")
	public BookDto updateBook(@Valid @RequestBody BookDto b, @PathVariable("bookId") Long bookId,
			@PathVariable("categoryId") Long categoryId) {
		return service.updateBook(b, bookId, categoryId);
	}

	@PutMapping("/{bookId}/updatetitle")
	public BookDto updateBookTitle(@Valid @RequestBody BookDto bookDto, @PathVariable("bookId") Long bookId) {
		return service.updateBookTitle(bookDto, bookId);
	}

	@GetMapping("/{bookId}/issuedto")
	public IssueBookDto getIssueBook(@PathVariable("bookId") Long bookId) {
		return service.getIssueBook(bookId);
	}

	@PostMapping("/{bookId}/uploadimage")
	public String uploadImage(@PathVariable("bookId") Long bookId,
			@RequestParam(name = "bookImage", required = true) MultipartFile bookImage)
			throws IllegalStateException, IOException {
		return service.uploadImage(bookId, bookImage);
	}

}
