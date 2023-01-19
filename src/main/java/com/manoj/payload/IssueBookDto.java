package com.manoj.payload;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class IssueBookDto {
	private Long id;

	@JsonManagedReference
	private BookDto book;

	private String issueDate;

	private String expiringDate;

	private Integer fine;
}
