package com.example.sistemaBanco.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;

import lombok.Data;

@Data
public class GetTransacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private BigDecimal valor;
	private Date data;
	private TipoTransacao tipo;
	private GetConta contaOrigem;// usar dto
	private GetConta contaDestino;

	public GetTransacao toTransacao(Transacao transacao) { // convertendo usuarioDto em um obj usuario
		this.id = transacao.getId();
        this.valor = transacao.getValor();
        this.data = transacao.getData();
        this.tipo = transacao.getTipo();
        
        this.contaOrigem = new GetConta().toConta(transacao.getContaOrigem());
        this.contaDestino = new GetConta().toConta(transacao.getContaDestino());
        return this;
	}
	
	public static List<GetTransacao> fromTransacao(List<Transacao> listTransacao){
		List<GetTransacao> getTransacaoList = new ArrayList<>();
		listTransacao.forEach(t -> {
			getTransacaoList.add(new GetTransacao().toTransacao(t));
		});
		return getTransacaoList;
	}

}
