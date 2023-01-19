package com.manoj.payload;

import com.manoj.util.ValidateDate;
import com.manoj.util.ValidateGender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class StudentDto {
	private Long id;

	@NotBlank(message = "stream can't be null or empty!")
	@Size(max = 20, message = "stream name should not exceed the max length of 20 characters!")
	private String stream;

	@NotBlank(message = "name can't be null or empty!")
	@Size(max = 20, message = "student name should not exceed the max length of 20 characters!")
	private String name;

	@NotBlank(message = "gender can't be null or empty!")
	@ValidateGender(message = "invalid gender type! valid gender types [male, female, others]!")
	private String gender;

	@NotNull(message = "date of birth can't be null")
	@ValidateDate(dateFormat = "dd-MM-yyyy", message = "invalid date! date must be in [dd-MM-yyyy] format!")
	private String dob;

	@NotNull(message = "contact number can't be null!")
	@Size(min = 10, max = 10, message = "contact number should have exact 10 characters!")
	@Pattern(regexp = "^[0-9]*$", message = "contact number must contain digits only!")
	private String contact;

	@Email(regexp = ".+[@].+[\\.].+", message = "invalid email!")
	@NotBlank(message = "email field can't be null or empty!")
	@Size(max = 35, message = "email name should not exceed the max length of 35 characters!")
	private String email;
	
	private String imageUrl;
}
