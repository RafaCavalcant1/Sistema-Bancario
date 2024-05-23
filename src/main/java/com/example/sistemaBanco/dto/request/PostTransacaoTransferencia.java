package com.example.sistemaBanco.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;

public class PostTransacaoTransferencia implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idContaOrigem;
	private Long idContaDestino;
	private BigDecimal valor;

	public PostTransacaoTransferencia() {
	}

	public PostTransacaoTransferencia(Long idContaOrigem, Long idContaDestino, BigDecimal valor) {
		super();
		this.idContaOrigem = idContaOrigem;
		this.idContaDestino = idContaDestino;
		this.valor = valor;
	}

	public Transacao toTransacao() {
		Conta contaOrigem = new Conta();
		contaOrigem.setId(idContaOrigem);

		Conta contaDestino = new Conta();
		contaDestino.setId(idContaDestino);

		return new Transacao(contaOrigem, contaDestino, valor, TipoTransacao.TRANSFERENCIA);
	}

	public Long getIdContaOrigem() {
		return idContaOrigem;
	}

	public void setIdContaOrigem(Long idContaOrigem) {
		this.idContaOrigem = idContaOrigem;
	}

	public Long getIdContaDestino() {
		return idContaDestino;
	}

	public void setIdContaDestino(Long idContaDestino) {
		this.idContaDestino = idContaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
