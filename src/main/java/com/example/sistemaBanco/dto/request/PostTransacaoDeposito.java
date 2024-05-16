package com.example.sistemaBanco.dto.request;

import java.io.Serializable;

import com.example.sistemaBanco.dto.IdDto;
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;

public class PostTransacaoDeposito implements Serializable {

	private static final long serialVersionUID = 1L;

	private IdDto conta;
	private Double valor;

	public PostTransacaoDeposito() {
	}

	public PostTransacaoDeposito(IdDto conta, Double valor) {
		super();
		this.conta = conta;
		this.valor = valor;
	}

	public Transacao toTransacao() {
		return new Transacao(null, null, conta.toConta(), valor, null, TipoTransacao.DEPOSITO);
	}

	public static PostTransacaoDeposito fromTransacaoDeposito(Transacao transacao) {
		return new PostTransacaoDeposito(
		new IdDto(transacao.getContaDestino().getId()), 
		transacao.getValor()
		);
	}

	public IdDto getConta() {
		return conta;
	}

	public void setConta(IdDto conta) {
		this.conta = conta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
