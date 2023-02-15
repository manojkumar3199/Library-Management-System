package com.manoj.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.manoj.payload.BookDto;
import com.manoj.payload.IssueBookDto;

public interface BookService {

	List<BookDto> getAllBooks(Integer pageSize, Integer pageNumber);

	BookDto getBook(Long id);

	BookDto saveBook(BookDto b, Long categoryId);

	BookDto updateBook(BookDto b, Long bookId, Long categoryId);

	void deleteBook(Long id) throws IOException;

	IssueBookDto getIssueBook(Long bookId);

	String uploadImage(Long bookId, MultipartFile bookImage) throws IllegalStateException, IOException;

	List<BookDto> getBooksByCategoryId(Long categoryId, Integer pageSize, Integer pageNumber);

}
