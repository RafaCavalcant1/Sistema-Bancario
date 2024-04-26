package com.example.sistemaBanco.service.exceptions;


public class InvalidPasswordLengthException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidPasswordLengthException(String message) {
		super(message);
	}
}
