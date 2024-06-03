package com.example.sistemaBanco.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.sistemaBanco.dto.IdDto;
import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;

import lombok.Data;

@Data
public class PostTransacaoSaque implements Serializable {

	private static final long serialVersionUID = 1L;

	private IdDto conta;
	private BigDecimal valor;

	public PostTransacaoSaque() {
	}

	public Transacao toTransacao() {
        return new Transacao.Builder()
                .contaOrigem(new Conta.Builder().id(this.conta.getId()).build())
                .valor(this.valor)
                .tipo(TipoTransacao.SAQUE)
                .build();
    }


}
