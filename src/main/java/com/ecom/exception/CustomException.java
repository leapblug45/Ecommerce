package com.ecom.exception;
//CustomException class for ease of exception handling throughout project
public class CustomException extends IllegalArgumentException{

	public CustomException(String msg) {
		super(msg);
	}
}
