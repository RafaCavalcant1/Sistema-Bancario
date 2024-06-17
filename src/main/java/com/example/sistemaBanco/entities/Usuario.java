package com.example.sistemaBanco.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.sistemaBanco.entities.enums.TipoUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_usuarios")
public class Usuario implements Serializable{ //interface marcadora que indica que a classe pode ser serializada.  é o processo de converter um objeto em uma sequência de bytes, que pode ser armazenada em um arquivo

	private static final long serialVersionUID = 1L;  // nnúmero de série padrao 
	
	@Id // PK 
	// para fazer com que o id fique autoincrementavel 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nomeCompleto;
	private String cpfCnpj;
	private String email;
	private String senha;
	private TipoUsuario tipo; // comum ou lojista
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER) // um usuario pode ter varias contas
	private List<Conta> contas = new ArrayList<>();
	
	public Usuario() {
    }

    public static class Builder {
        private Long id;
        private String nomeCompleto;
        private String cpfCnpj;
        private String email;
        private String senha;
        private TipoUsuario tipo;
        private List<Conta> contas = new ArrayList<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nomeCompleto(String nomeCompleto) {
            this.nomeCompleto = nomeCompleto;
            return this;
        }

        public Builder cpfCnpj(String cpfCnpj) {
            this.cpfCnpj = cpfCnpj;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public Builder tipo(TipoUsuario tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder contas(List<Conta> contas) {
            this.contas = contas;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }
    
    private Usuario(Builder builder) {
        this.id = builder.id;
        this.nomeCompleto = builder.nomeCompleto;
        this.cpfCnpj = builder.cpfCnpj;
        this.email = builder.email;
        this.senha = builder.senha;
        this.tipo = builder.tipo;
        this.contas = builder.contas;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public List<Conta> getContas() {
	     return contas;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	

}
