package com.manoj.service;

import java.util.List;

import com.manoj.payload.IssueBookDto;

public interface IssueBookService {

	List<IssueBookDto> getAllIssuedBooks(Integer pageSize, Integer pageNumber);

}