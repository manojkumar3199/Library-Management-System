package com.manoj.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class BookDto {
	private Long id;

	@NotBlank(message = "title can't be null or empty!")
	@Size(max = 50, message = "book title should not exceed the max length of 50 characters!")
	private String title;

	@NotBlank(message = "author can't be null or empty!")
	@Size(max = 20, message = "author name should not exceed the max length of 20 characters!")
	private String author;

	private String description;

	private String imageUrl;

	private CategoryDto category;
}
