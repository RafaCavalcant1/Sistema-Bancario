package com.example.sistemaBanco.dto;

import java.io.Serializable;

public class IdDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    public IdDto() {
    }

	public IdDto(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    

}
