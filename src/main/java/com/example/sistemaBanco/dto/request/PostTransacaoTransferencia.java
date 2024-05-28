package com.example.sistemaBanco.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;

import lombok.Data;

@Data
public class PostTransacaoTransferencia implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idContaOrigem;
	private Long idContaDestino;
	private BigDecimal valor;

	public Transacao toTransacao() {
		Conta contaOrigem = new Conta();
		contaOrigem.setId(idContaOrigem);

		Conta contaDestino = new Conta();
		contaDestino.setId(idContaDestino);

		return new Transacao(contaOrigem, contaDestino, valor, TipoTransacao.TRANSFERENCIA);
	}

}
