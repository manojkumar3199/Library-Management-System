package com.manoj.payload;

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
public class StatsDto {
	private Long totalStudents;
	private Long totalCategories;
	private Long totalBooks;
	private Long totalIssuedBooks;
}
