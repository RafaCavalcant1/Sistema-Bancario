package com.example.sistemaBanco.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.sistemaBanco.dto.IdDto;
import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;

import lombok.Data;

@Data
public class PostTransacaoDeposito implements Serializable {

	private static final long serialVersionUID = 1L;

	private IdDto conta;
	private BigDecimal valor;

	public Transacao toTransacao() {
		Conta contaDestino = new Conta();
        contaDestino.setId(this.conta.getId());
		return new Transacao(contaDestino, valor, TipoTransacao.DEPOSITO);
	}
}
