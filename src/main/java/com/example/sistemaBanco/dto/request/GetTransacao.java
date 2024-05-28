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
	
	public List<GetTransacao>fromTransferencia(List<Transacao> listTransacao){
		GetTransacao getTransacao = new GetTransacao();
		List<GetTransacao> getTransacaoList = new ArrayList<>();
		listTransacao.forEach(t -> {
			getTransacaoList.add(getTransacao.toTransacao(t));
		});
		return getTransacaoList;
	}


//	public static GetTransacao fromTransferencia(Transacao transferencia) { // faz o inverso e retorna os objetps
//		GetConta contaOrigem = new GetConta();
//		contaOrigem.toConta(transferencia.getContaOrigem());
//
//		GetConta contaDestino = new GetConta();
//		contaDestino.toConta(transferencia.getContaDestino());
//
//		return new GetTransacao(transferencia.getId(), contaOrigem, contaDestino, transferencia.getValor(),
//				transferencia.getData(), transferencia.getTipo());
//	}

}
