package com.example.sistemaBanco.dto;

import java.io.Serializable;

import com.example.sistemaBanco.dto.response.ResponseUsuario;
import com.example.sistemaBanco.entities.Conta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class GetConta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String conta;
	private String agencia;
	private Double saldo;

	// faz com que qnd o jackson for converter java em json eme não vai considerar a propiedade contas dentro de usuario, ele vai ignorar
	@JsonIgnoreProperties("contas") 
	private ResponseUsuario usuario;// usar o dto 

	public GetConta() {
	}

	public GetConta(Long id,String conta, String agencia, Double saldo, ResponseUsuario usuario) {
		super();
		this.id = id;
		this.conta = conta;
		this.agencia = agencia;
		this.saldo = saldo;
		this.usuario = usuario;
	}

	public Conta toConta() { // convertendo contaDto em um obj usuario
		return new Conta(this.getId(), this.getConta(), this.getAgencia(), this.getSaldo(), null);
	}

	public static GetConta fromConta(Conta conta) {
		ResponseUsuario getUsuario = ResponseUsuario.toResponseUsuario(conta.getUsuario());// pegando o getusuario para converter em usuario associando a uma conta
		return new GetConta(conta.getId(), conta.getConta(), conta.getAgencia(), conta.getSaldo(), getUsuario);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public ResponseUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(ResponseUsuario usuario) {
		this.usuario = usuario;
	}

	

	
	

}
