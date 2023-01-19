package com.manoj.exception;

import lombok.Getter;

@Getter
public class ForeignKeyConstraintException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;
	private String description;

	public ForeignKeyConstraintException() {
		super();
	}

	public ForeignKeyConstraintException(String message) {
		super(message);
		this.message = message;
	}

	public ForeignKeyConstraintException(String message, String description) {
		this.message = message;
		this.description = description;
	}
}
