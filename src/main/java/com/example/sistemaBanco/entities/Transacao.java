package com.example.sistemaBanco.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.example.sistemaBanco.entities.enums.TipoTransacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_transacoes")
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // PK
	// para fazer com que o id fique autoincrementavel
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal valor;
	private Date data;
	private TipoTransacao tipo;

	@ManyToOne // uma transferência pertence a uma única conta de origem
	@JoinColumn(name = "contaOrigem") // chave estrangeira
	private Conta contaOrigem;

	@ManyToOne // uma transferência pertence a uma única conta de destino
	@JoinColumn(name = "contaDestino") // chave estrandeira
	private Conta contaDestino;

	public Transacao() {
	}

	public Transacao(Long id, Conta contaOrigem, Conta contaDestino, BigDecimal valor, Date data, TipoTransacao tipo) {
		this.id = id;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.data = data;
		this.tipo = tipo;
	}
	
	public Transacao(Conta contaDestino,BigDecimal valor, TipoTransacao tipo) {
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public Transacao(Conta contaOrigem, Conta contaDestino,BigDecimal valor, TipoTransacao tipo) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		return Objects.equals(id, other.id);
	}

}
