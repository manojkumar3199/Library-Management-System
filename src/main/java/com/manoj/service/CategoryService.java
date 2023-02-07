package com.manoj.service;

import java.io.IOException;
import java.util.List;

import com.manoj.payload.CategoryDto;

public interface CategoryService {

	List<CategoryDto> getAllCategory(Integer pageSize, Integer pageNumber);

	CategoryDto getCategory(Long id);

	CategoryDto saveCategory(CategoryDto c);

	CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

	void deleteCategory(Long id) throws IOException;

}
