package com.example.sistemaBanco.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.sistemaBanco.dto.response.ResponseUsuario;
import com.example.sistemaBanco.entities.Conta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class GetConta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String conta;
	private String agencia;
	private BigDecimal saldo;

	// faz com que qnd o jackson for converter java em json eme não vai considerar a propiedade contas dentro de usuario, ele vai ignorar
	@JsonIgnoreProperties("contas") 
	private ResponseUsuario usuario;// usar o dto 
	
	public GetConta toConta(Conta conta) { 
		this.id = conta.getId();
        this.conta = conta.getConta();
        this.agencia = conta.getAgencia();
        this.saldo = conta.getSaldo();
       
        ResponseUsuario responseUsuario = new ResponseUsuario();
        this.usuario = responseUsuario.toResponseUsuario(conta.getUsuario());
        return this;
	}

	public List<GetConta> fromConta(List<Conta> listConta){
		GetConta getConta = new GetConta();
		List<GetConta> getContaList = new ArrayList<>();
		listConta.forEach(c -> {
			getContaList.add(getConta.toConta(c));
		});
		return getContaList;
	}

}
