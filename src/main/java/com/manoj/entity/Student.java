package com.manoj.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class Student {
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	@Column(name = "student_id")
	private Long id;

	@Column(length = 20)
	private String stream;

	@Column(length = 20)
	private String name;

	@Column(length = 20)
	private String gender;

	@Column(name = "date_of_birth", length = 15)
	private String dob;

	@Column(name = "contact_number", length = 15)
	private String contact;

	@Column(length = 35)
	private String email;
	
	@Column(name = "student_image")
	private String image;

	@Column(name = "student_image_url")
	private String imageUrl;

	@OneToMany(mappedBy = "student")
	private Set<IssueBook> issueBooks;
}
