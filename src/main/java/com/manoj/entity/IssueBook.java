package com.manoj.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "issue_books", uniqueConstraints = @UniqueConstraint(columnNames = { "book_id" }))
public class IssueBook {
	@Id
	@SequenceGenerator(name = "issue_sequence", sequenceName = "issue_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_sequence")
	@Column(name = "issue_id")
	private Long id;

	@Column(name = "issue_date")
	private String issueDate;

	@Column(name = "expiring_date")
	private String expiringDate;

	private Integer fine;

	@OneToOne()
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
}
