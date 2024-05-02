package com.example.sistemaBanco.service.exceptions;

public class ContasIguaisException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ContasIguaisException (String message) {
		super(message);
	}
	

}
