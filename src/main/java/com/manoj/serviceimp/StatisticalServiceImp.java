package com.manoj.serviceimp;

import org.springframework.stereotype.Service;

import com.manoj.payload.StatsDto;
import com.manoj.repository.BookRepo;
import com.manoj.repository.CategoryRepo;
import com.manoj.repository.IssueBookRepo;
import com.manoj.repository.StudentRepo;
import com.manoj.service.StatisticalService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticalServiceImp implements StatisticalService {
	private StudentRepo studentRepo;
	private CategoryRepo categoryRepo;
	private BookRepo bookRepo;
	private IssueBookRepo issueBookRepo;

	@Override
	public StatsDto getStats() {
		StatsDto currentStats = new StatsDto();

		Long toalStudents = studentRepo.count();
		Long totalCategories = categoryRepo.count();
		Long totalBooks = bookRepo.count();
		Long totalIssuedBooks = issueBookRepo.count();

		currentStats.setTotalStudents(toalStudents);
		currentStats.setTotalCategories(totalCategories);
		currentStats.setTotalBooks(totalBooks);
		currentStats.setTotalIssuedBooks(totalIssuedBooks);

		return currentStats;
	}
}
