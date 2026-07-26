package com.scm.scm10.helper;

public class ResourceNotFoundExeception extends RuntimeException {

	public 	ResourceNotFoundExeception(String message)
	{
		super(message);
		
	}
	
	public ResourceNotFoundExeception()
	{
		super("resouce not found ");
	}
}
