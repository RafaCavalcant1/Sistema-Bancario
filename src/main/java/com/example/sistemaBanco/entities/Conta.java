package com.example.sistemaBanco.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contas")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String conta;
	private String agencia;
	private BigDecimal saldo; 

	
	@ManyToOne(fetch = FetchType.EAGER) //(fetch = FetchType.LAZY)// uma conta pode pertencer a um unico usuario
	private Usuario usuario;

	public Conta() {
    }

    public static class Builder {
        private Long id;
        private String conta;
        private String agencia;
        private BigDecimal saldo;
        private Usuario usuario;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder conta(String conta) {
            this.conta = conta;
            return this;
        }

        public Builder agencia(String agencia) {
            this.agencia = agencia;
            return this;
        }

        public Builder saldo(BigDecimal saldo) {
            this.saldo = saldo;
            return this;
        }

        public Builder usuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public Conta build() {
            return new Conta(this);
        }
    }
    
    private Conta(Builder builder) {
        this.id = builder.id;
        this.conta = builder.conta;
        this.agencia = builder.agencia;
        this.saldo = builder.saldo;
        this.usuario = builder.usuario;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Conta other = (Conta) obj;
		return Objects.equals(id, other.id);
	}

}
