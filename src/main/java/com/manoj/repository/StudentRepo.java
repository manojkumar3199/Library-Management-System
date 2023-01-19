package com.manoj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manoj.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
	public Optional<Student> findByEmail(String email);
}
