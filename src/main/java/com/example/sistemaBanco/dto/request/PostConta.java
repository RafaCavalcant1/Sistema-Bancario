package com.example.sistemaBanco.dto.request;

import java.io.Serializable;

import com.example.sistemaBanco.dto.IdDto;


public class PostConta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String agencia;
    private IdDto usuario;

    public PostConta() {
    }

    public PostConta(String agencia, IdDto usuario) {
        this.agencia = agencia;
        this.usuario = usuario;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public IdDto getUsuario() {
        return usuario;
    }

    public void setUsuario(IdDto usuario) {
        this.usuario = usuario;
    }
}


    

