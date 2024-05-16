package com.example.sistemaBanco.dto.request;

import java.io.Serializable;

import com.example.sistemaBanco.dto.IdDto;
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;

public class PostTransacaoSaque implements Serializable {

	private static final long serialVersionUID = 1L;

	private IdDto conta;
	private Double valor;

	public PostTransacaoSaque() {
	}

	public PostTransacaoSaque(IdDto conta, Double valor) {
		super();
		this.conta = conta;
		this.valor = valor;
	}

	public Transacao toTransacao() { 
		return new Transacao(null, conta.toConta(), null, valor, null, TipoTransacao.SAQUE);
	}

	public static PostTransacaoSaque fromTransacaoSaque(Transacao transacao) { 
		return new PostTransacaoSaque(
			new IdDto(transacao.getContaOrigem().getId()), 
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
