package com.example.sistemaBanco.entities.enums;

public enum TipoUsuario {
	
	COMUM("comum"),
    LOJISTA("lojista");
    
    private String tipo;
    
    TipoUsuario(String tipo){
    	this.tipo = tipo;
    }
    
    public String getTipo() {
    	return tipo;
    }
}
