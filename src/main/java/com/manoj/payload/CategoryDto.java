package com.manoj.payload;

import jakarta.validation.constraints.NotBlank;
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
public class CategoryDto {
	private Long id;

	@NotBlank(message = "category name can't be null or empty!")
	private String categoryName;

	@NotBlank(message = "short name can't be null or empty!")
	private String shortName;
}
