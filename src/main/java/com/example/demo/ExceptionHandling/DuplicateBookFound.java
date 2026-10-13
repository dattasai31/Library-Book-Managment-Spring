package com.example.demo.ExceptionHandling;

public class DuplicateBookFound extends RuntimeException{
	public DuplicateBookFound(String message)
	{
		super(message);
	}

}
