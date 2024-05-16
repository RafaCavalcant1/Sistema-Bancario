package com.example.sistemaBanco.dto;

import java.io.Serializable;

import com.example.sistemaBanco.entities.Conta;

public class IdDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    public IdDto() {
    }

	public IdDto(Long id) {
		super();
		this.id = id;
	}
	
    public Conta toConta() {
        Conta conta = new Conta();
        conta.setId(this.id);
        return conta;
    }

    public static IdDto fromConta(Conta conta) {
        return new IdDto(conta.getId());
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    

}
