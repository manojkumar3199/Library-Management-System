package com.manoj.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.manoj.payload.CategoryDto;
import com.manoj.service.CategoryService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

	private CategoryService service;

	@GetMapping("")
	public List<CategoryDto> getAllCategories(
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber) {
		return service.getAllCategory(pageSize, pageNumber);
	}

	@GetMapping("/{id}")
	public CategoryDto getCategory(@PathVariable("id") Long id) {
		return service.getCategory(id);
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CategoryDto saveCategory(@Valid @RequestBody CategoryDto c) {
		return service.saveCategory(c);
	}

	@Transactional
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable("id") Long id) throws IOException {
		service.deleteCategory(id);
	}

	@PutMapping("/{categoryId}")
	public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("categoryId") Long categoryId) {
		return service.updateCategory(categoryDto, categoryId);
	}

	@PutMapping("/{categoryId}/updatename")
	public CategoryDto updateCategoryName(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("categoryId") Long categoryId) {
		return service.updateCategoryName(categoryDto, categoryId);
	}
}
