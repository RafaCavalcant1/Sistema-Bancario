package com.example.sistemaBanco.dto.request;

import java.io.Serializable;

import com.example.sistemaBanco.dto.IdDto;

import lombok.Data;

@Data
public class PostConta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String agencia;
    private IdDto usuario;
}


    

