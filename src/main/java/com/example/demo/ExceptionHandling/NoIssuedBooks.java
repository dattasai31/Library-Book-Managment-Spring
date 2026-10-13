package com.example.demo.ExceptionHandling;

public class NoIssuedBooks extends RuntimeException {
	
	public NoIssuedBooks(String message)
	{
		super(message);
	}

}
