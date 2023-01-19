package com.manoj.exception;

public class CustomResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomResourceNotFoundException() {
		super();
	}

	public CustomResourceNotFoundException(String message) {
		super(message);
	}
}
