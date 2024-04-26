package com.example.sistemaBanco.entities;

import java.io.Serializable;
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
@Table(name = "tb_transferencias")
public class Transferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id // PK 
	// para fazer com que o id fique autoincrementavel 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private Date data;
    private TipoTransacao tipo;
    
    @ManyToOne//uma transferência pertence a uma única conta de origem
    @JoinColumn(name = "contaOrigem") // chave estrangeira
    private Conta contaOrigem;

    @ManyToOne //uma transferência pertence a uma única conta de destino
    @JoinColumn(name = "contaDestino")// chave estrandeira
    private Conta contaDestino;

    public Transferencia() {
    }

    public Transferencia(Long id, Conta contaOrigem, Conta contaDestino, Double valor, Date data, TipoTransacao tipo) {
        this.id = id;
    	this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = data;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
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
		Transferencia other = (Transferencia) obj;
		return Objects.equals(id, other.id);
	}
    
    

}
