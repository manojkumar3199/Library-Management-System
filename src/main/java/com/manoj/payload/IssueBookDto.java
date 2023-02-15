package com.manoj.payload;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonFilter("IssueBookDto")
public class IssueBookDto {
	private Long id;

	private BookDto book;

	private String issueDate;

	private String expiringDate;

	private Integer fine;

	private StudentDto student;
}
