package com.example.sistemaBanco.service.exceptions;

public class DuplicateUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateUserException(String message) {
		super(message);
	}
}
