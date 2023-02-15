package com.manoj.controller;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.manoj.payload.IssueBookDto;
import com.manoj.service.IssueBookService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/issuebook")
public class IssueBookController {
	private IssueBookService issueBookService;

	@GetMapping("")
	public MappingJacksonValue getAllIssuedBooks(
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber) {
		List<IssueBookDto> issuedBooks = issueBookService.getAllIssuedBooks(pageSize, pageNumber);
		// applying filter
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("IssueBookDto",
						SimpleBeanPropertyFilter.filterOutAllExcept("id", "book", "student", "issueDate",
								"expiringDate", "fine"))
				.addFilter("BookDto", SimpleBeanPropertyFilter.filterOutAllExcept("id", "title", "author",
						"description", "imageUrl", "category"));
		MappingJacksonValue mapping = new MappingJacksonValue(issuedBooks);
		mapping.setFilters(filters);
		return mapping;
	}
}
