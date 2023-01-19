package com.manoj.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manoj.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
	@Query("FROM Category")
	Set<Category> findAllCategory();

	Optional<Category> findByCategoryName(String categoryName);
}
