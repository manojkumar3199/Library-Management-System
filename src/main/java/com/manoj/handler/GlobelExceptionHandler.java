package com.manoj.handler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.manoj.exception.CustomResourceNotFoundException;
import com.manoj.exception.ForeignKeyConstraintException;
import com.manoj.exception.ResourceDuplicateException;

@RestControllerAdvice
public class GlobelExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, Object> handelCustomResourceNotFoundException(CustomResourceNotFoundException ex) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss z");
		ZonedDateTime currentTime = ZonedDateTime.now();

		HashMap<String, Object> res = new HashMap<>();
		res.put("status", HttpStatus.NOT_FOUND.value());
		res.put("error", HttpStatus.NOT_FOUND.name());
		res.put("timestamp", currentTime.format(dtf));
		res.put("message", ex.getMessage());
		return res;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss z");
		ZonedDateTime currentTime = ZonedDateTime.now();

		HashMap<String, Object> res = new HashMap<>();
		res.put("status", HttpStatus.BAD_REQUEST.value());
		res.put("error", HttpStatus.BAD_REQUEST.name());
		res.put("timestamp", currentTime.format(dtf));
		res.put("message", ex.getFieldError().getDefaultMessage());
		return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceDuplicateException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public Map<String, ?> handleResourceDuplicateException(ResourceDuplicateException ex) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss z");
		ZonedDateTime currentTime = ZonedDateTime.now();

		HashMap<String, Object> res = new HashMap<>();
		res.put("status", HttpStatus.CONFLICT.value());
		res.put("error", HttpStatus.CONFLICT.name());
		res.put("timestamp", currentTime.format(dtf));
		res.put("message", ex.getMessage());

		return res;
	}

	@ExceptionHandler(ForeignKeyConstraintException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public Map<String, Object> handleForeignKeyConstraintException(ForeignKeyConstraintException ex) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss z");
		ZonedDateTime currentTime = ZonedDateTime.now();

		HashMap<String, Object> res = new HashMap<>();
		res.put("status", HttpStatus.CONFLICT.value());
		res.put("error", HttpStatus.CONFLICT.name());
		res.put("timestamp", currentTime.format(dtf));
		res.put("message", ex.getMessage());

		return res;
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Object> handelMultipartException(MultipartException ex) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss z");
		ZonedDateTime currentTime = ZonedDateTime.now();

		HashMap<String, Object> res = new HashMap<>();
		res.put("status", HttpStatus.BAD_REQUEST.value());
		res.put("error", HttpStatus.BAD_REQUEST.name());
		res.put("timestamp", currentTime.format(dtf));
		res.put("message", ex.getMessage());

		return res;
	}

}