package com.example.sistemaBanco.dto;

public class ResponseEmailDto {

	private String resultado;
	
	public ResponseEmailDto() {
	}

	public ResponseEmailDto(String resultado) {
		super();
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	
}
