package com.manoj.exception;

public class ResourceDuplicateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceDuplicateException() {
		super();
	}

	public ResourceDuplicateException(String message) {
		super(message);
	}

}
