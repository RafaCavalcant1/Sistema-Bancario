package com.example.sistemaBanco.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;


public class GetTransacao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private BigDecimal valor;
    private Date data;
    private TipoTransacao tipo;
    private GetConta contaOrigem;// usar  dto 
    private GetConta contaDestino;

    public GetTransacao() {
    }

    public GetTransacao(Long id, GetConta contaOrigem, GetConta contaDestino, BigDecimal valor, Date data, TipoTransacao tipo) {
    	this.id = id;
    	this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
    }

    public Transacao toTransacao() { // convertendo usuarioDto em um obj usuario
		return new Transacao(this.getId(),null,  null, this.getValor(), this.getData(), this.getTipo());
	}

	public static GetTransacao fromTransferencia(Transacao transferencia) { // faz  o inverso e retorna os objetps 
		return new  GetTransacao(transferencia.getId(),GetConta.fromConta(transferencia.getContaOrigem()), GetConta.fromConta(transferencia.getContaDestino()), transferencia.getValor(), 
				transferencia.getData(), transferencia.getTipo());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoTransacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}

	public GetConta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(GetConta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public GetConta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(GetConta contaDestino) {
		this.contaDestino = contaDestino;
	}
	

	

}
