package com.manoj.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.manoj.payload.IssueBookDto;
import com.manoj.payload.StudentDto;

public interface StudentService {

	List<StudentDto> getAllStudents(Integer pageSize, Integer pageNumber);

	StudentDto getStudent(Long id);

	StudentDto saveStudent(StudentDto s);

	StudentDto updateStudent(StudentDto s, Long id);

	void deleteStudent(Long id) throws IOException;

	IssueBookDto issueBook(Long studentId, Long bookId);

	void returnIssuedBook(Long studentId, Long bookId);

	List<IssueBookDto> getAllIssueBooks(Long studentId, Integer pageSize, Integer pageNumber);

	String uploadImage(Long studentId, MultipartFile studentImage) throws IllegalStateException, IOException;

}