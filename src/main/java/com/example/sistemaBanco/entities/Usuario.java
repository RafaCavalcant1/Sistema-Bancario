package com.example.sistemaBanco.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
// userDatails vem do ss e é usada para identificar uma classe que representa um usuário que vai ser autenticado 
// é necessario implementar os métodos dela
public class Usuario implements Serializable, UserDetails{ //interface marcadora que indica que a classe pode ser serializada.  é o processo de converter um objeto em uma sequência de bytes, que pode ser armazenada em um arquivo

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

	@Override
	// quando o SS for consultar a entidade p ver quais são as roles que o usuário tem ai chama esse função
	//aqui que temos que retornar quais são as roles do usuario para que o SS tome as decisões corretas de acordo com as roles
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// o SS ja tem mapeado por padrão algumas roles e ele espera receber uma coleçao de roles
		if(this.tipo == TipoUsuario.COMUM)return List.of(new SimpleGrantedAuthority("TIPO.COMUM"), new SimpleGrantedAuthority("TIPO.LOJISTA"));
		else return List.of(new SimpleGrantedAuthority("TIPO.LOJISTA"));

	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		// aqui retorna o login do usuario (email)
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	// ve se a credencial não está inspirada
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// o original o return é false 
	@Override
	// ve se o usuario está ativo 
	public boolean isEnabled() {
		return true;
	}
	
	
	
	

	

}
