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
		// inicia o builder da transacao
	    return new Transacao.Builder()
	            .contaOrigem(new Conta.Builder().id(idContaOrigem).build()) // define a contaOrigem e inicia o builder da CONTA
	            .contaDestino(new Conta.Builder().id(idContaDestino).build())
	            .valor(valor)
	            .tipo(TipoTransacao.TRANSFERENCIA)
	            //constroi e retorna a instância de Transacao com os valores definidos
	            .build();
	}

}
