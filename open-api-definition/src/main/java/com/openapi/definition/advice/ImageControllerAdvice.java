package com.openapi.definition.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.openapi.definition.exception.EmptyInputException;

@ControllerAdvice
public class ImageControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EmptyInputException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
		
		return new ResponseEntity<String>("Input Field is Empty", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException){
		
		return new ResponseEntity<String>("No Value is Present in DB", HttpStatus.NOT_FOUND);
	}
}
