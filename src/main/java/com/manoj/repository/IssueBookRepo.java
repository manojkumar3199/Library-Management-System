package com.manoj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manoj.entity.Book;
import com.manoj.entity.IssueBook;
import com.manoj.entity.Student;

public interface IssueBookRepo extends JpaRepository<IssueBook, Long> {
	public List<IssueBook> findByStudent(Student s);

	public Optional<IssueBook> findByBook(Book book);

	@Query(value = "FROM IssueBook AS i WHERE i.student = :savedStudent")
	public Page<IssueBook> customFindByStudent(@Param("savedStudent") Student student, Pageable pageable);

//	@Query(value = "FROM IssueBook")
//	public Page<IssueBook> customFindAll(Pageable pageable);
}
