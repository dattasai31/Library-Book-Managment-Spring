package com.example.demo.ExceptionHandling;

public class BookNotFound extends RuntimeException {

	public BookNotFound(String message)
	{
		super(message);
	}
}
