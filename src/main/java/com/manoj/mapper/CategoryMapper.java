package com.manoj.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.manoj.entity.Category;
import com.manoj.payload.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryDto categoryEntityToCategoryDto(Category categoryEntity);

	Category categoryDtoToCategoryEntity(CategoryDto categoryDto);

	List<CategoryDto> categoryEntityListToCategoryDtoList(List<Category> categoryEntityList);
}
