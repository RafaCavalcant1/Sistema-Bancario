package com.example.sistemaBanco.service.exceptions;

public class ContaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ContaNotFoundException(Object id) {
		super("account not found. Id " + id);
	}

}
