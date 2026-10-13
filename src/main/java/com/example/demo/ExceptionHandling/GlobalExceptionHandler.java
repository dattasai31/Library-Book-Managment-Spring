package com.example.demo.ExceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> handleFieldException(MethodArgumentNotValidException ex)
	{
		Map<String,String> errors=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->
		{
			errors.put(error.getField(), error.getDefaultMessage());
		}
				);
		return errors;
		
	}
	
	@ExceptionHandler(BookNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleBookNotFoundException(BookNotFound b)
	{
		return b.getMessage();
	}
	
	@ExceptionHandler(NoIssuedBooks.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNoIssuedBooksException(NoIssuedBooks n)
	{
		return n.getMessage();
	}
	
	@ExceptionHandler(NoOverdueBooks.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNoOverdueBooks(NoOverdueBooks n)
	{
		return n.getMessage();
	}
	
	@ExceptionHandler(DuplicateBookFound.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleDuplicateBookFound(DuplicateBookFound e)
	{
		return e.getMessage();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGeneralExceptio(Exception e)
	{
		return "Something went wrong";
	}
	
	

}
