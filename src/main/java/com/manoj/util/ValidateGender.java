package com.manoj.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface ValidateGender {

	String message() default "invalid gender!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

class GenderValidator implements ConstraintValidator<ValidateGender, String> {

	@Override
	public boolean isValid(String gender, ConstraintValidatorContext context) {
		if (gender == null) {
			return true;
		}

		for (GenderCode genderCode : GenderCode.values()) {
			if (gender.equals(genderCode.getGender()))
				return true;
		}

		return false;
	}
}