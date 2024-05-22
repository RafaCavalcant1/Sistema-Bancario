package com.example.sistemaBanco.service;

public class ContaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ContaExistenteException(String message) {
		super(message);
	}

}
