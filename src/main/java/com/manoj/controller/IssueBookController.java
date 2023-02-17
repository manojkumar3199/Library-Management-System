package com.manoj.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manoj.payload.IssueBookDto;
import com.manoj.service.IssueBookService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/issuebook")
public class IssueBookController {
	private IssueBookService issueBookService;

	@GetMapping("")
	public List<IssueBookDto> getAllIssuedBooks(
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber) {
		return issueBookService.getAllIssuedBooks(pageSize, pageNumber);
	}
}