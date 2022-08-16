package com.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {
//ExceptionHandler for our CustomExceptions used in Controller classes
	@ExceptionHandler(value = CustomException.class)
	public final ResponseEntity<String> handleCustomException(CustomException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AuthenticationFailedException.class)
	public final ResponseEntity<String> handleAuthenticationFailedException(AuthenticationFailedException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
