package com.example.sistemaBanco.service.exceptions;

public class TransferenciaNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public TransferenciaNotFoundException(Object id) {
		super("transfer not found. Id " + id);
	}
	

}
