package com.example.sistemaBanco.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.sistemaBanco.dto.IdDto;

public class ResponseConta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String conta;
	private String agencia;
	private BigDecimal saldo;
	private IdDto usuario;

	public ResponseConta() {
	}

	public ResponseConta(Long id, String conta, String agencia, BigDecimal saldo, IdDto usuario) {
		super();
		this.id = id;
		this.conta = conta;
		this.agencia = agencia;
		this.saldo = saldo;
		this.usuario = usuario;
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

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public IdDto getUsuario() {
		return usuario;
	}

	public void setUsuario(IdDto usuario) {
		this.usuario = usuario;
	}

}
