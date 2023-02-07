package com.manoj.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manoj.entity.Book;
import com.manoj.entity.Category;

public interface BookRepo extends JpaRepository<Book, Long> {

	@Query("FROM Book")
	Set<Book> findAllBook();

	Set<Book> findByCategory(Category category);

	void deleteAllByCategory(Category category);

	Optional<Book> findByTitleIgnoreCase(String title);

	Optional<Book> findByTitleIgnoreCaseAndIdNot(String title, Long bookId);
}
