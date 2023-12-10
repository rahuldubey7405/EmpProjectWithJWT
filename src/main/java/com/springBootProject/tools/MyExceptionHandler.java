package com.springBootProject.tools;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MyExceptionHandler {

	// Other exception handling methods can be added here for different types of
	// exceptions

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity exceptionHanderNull(Exception ex, HttpServletRequest request) {
		ApiErrorRespose error = new ApiErrorRespose();
		error.setError("Null pointer exception has occured!!");
		error.setErrorMessage(ex.getMessage());
		error.setStatus(500);
		error.setTimeStamp(LocalDateTime.now().toString());

		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<String> exceptionHanderNumberFormatException(Exception ex, WebRequest request) {
		ApiErrorRespose error = new ApiErrorRespose();
		error.setError("Number formate exception has occured!!");
		error.setErrorMessage(ex.getMessage());
		error.setStatus(500);
		error.setTimeStamp(LocalDateTime.now().toString());
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {
		ApiErrorRespose error = new ApiErrorRespose();
		error.setError("something went wrong!!");
		error.setErrorMessage(ex.getMessage());
		error.setStatus(500);
		error.setTimeStamp(LocalDateTime.now().toString());
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
