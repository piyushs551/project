package com.neu.tools.exception;

public class PostException extends Exception {
	
	public PostException(String message)
	{
		super("postException-"+ message);
	}
	
	public PostException(String message, Throwable cause)
	{
		super("PostException-"+ message,cause);
	}
	
}
