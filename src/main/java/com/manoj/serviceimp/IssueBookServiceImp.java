package com.manoj.serviceimp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manoj.entity.IssueBook;
import com.manoj.exception.CustomResourceNotFoundException;
import com.manoj.mapper.IssueBookMapper;
import com.manoj.payload.IssueBookDto;
import com.manoj.repository.IssueBookRepo;
import com.manoj.service.IssueBookService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IssueBookServiceImp implements IssueBookService {

	private IssueBookRepo issueBookRepo;
	private IssueBookMapper issueBookMapper;

	@Override
	public List<IssueBookDto> getAllIssuedBooks(Integer pageSize, Integer pageNumber) {
		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<IssueBook> issuedBooksPage = issueBookRepo.findAll(p);
		List<IssueBook> issuedBooks = issuedBooksPage.getContent();

		if (issuedBooks.isEmpty())
			throw new CustomResourceNotFoundException("issued books list is empty!");

		return issueBookMapper.issueBookEntityListToIssueBookDtoList(issuedBooks);
	}

}
