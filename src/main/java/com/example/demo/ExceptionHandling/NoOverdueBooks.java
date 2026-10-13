package com.example.demo.ExceptionHandling;

public class NoOverdueBooks extends RuntimeException {

	public NoOverdueBooks(String message)
	{
		super(message);
	}
}
