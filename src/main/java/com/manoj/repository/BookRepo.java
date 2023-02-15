package com.manoj.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manoj.entity.Book;
import com.manoj.entity.Category;

public interface BookRepo extends JpaRepository<Book, Long> {

	@Query("FROM Book")
	Set<Book> findAllBook();

	List<Book> findByCategory(Category category);

	@Query(value = "FROM Book AS b WHERE b.category = :savedCategory")
	Page<Book> customFindByCategory(@Param("savedCategory") Category savedCategory, Pageable pageable);

	void deleteAllByCategory(Category category);

	Optional<Book> findByTitleIgnoreCase(String title);

	Optional<Book> findByTitleIgnoreCaseAndIdNot(String title, Long bookId);
}
