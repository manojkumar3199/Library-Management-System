package com.manoj.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface ValidateDate {
	String dateFormat();

	String message() default "invalid date!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

class DateValidator implements ConstraintValidator<ValidateDate, String> {
	private String dateFormat;

	@Override
	public void initialize(ValidateDate constraintAnnotation) {
		this.dateFormat = constraintAnnotation.dateFormat();
	}

	@Override
	public boolean isValid(String dateString, ConstraintValidatorContext context) {
		if (dateString == null) {
			return true;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);
			sdf.parse(dateString);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
}