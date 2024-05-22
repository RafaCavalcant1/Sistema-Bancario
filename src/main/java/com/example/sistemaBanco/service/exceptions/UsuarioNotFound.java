package com.example.sistemaBanco.service.exceptions;

public class UsuarioNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNotFound(Object id) {
		super("Resource not found. Id " + id);
	}

}
