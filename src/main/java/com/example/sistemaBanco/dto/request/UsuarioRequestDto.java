package com.example.sistemaBanco.dto.request;

import java.io.Serializable;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.TipoUsuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDto implements Serializable { // interface marcadora que indica que a classe pode ser
															// serializada. é
	// o processo de converter um objeto em uma sequência de bytes, que
	// pode ser armazenada em um arquivo

	private static final long serialVersionUID = 1L; // nnúmero de série padrao

	@NotBlank(message = "Name cannot be empty.")
	private String nomeCompleto;

	@NotBlank(message = "Cpf/cnpj cannot be empty.")
	private String cpfCnpj;

	@NotBlank(message = "Email cannot be empty.")
	@Email(message = "Invalid e-mail format.")
	private String email;

	@NotBlank(message = "Password cannot be empty.")
	@Size(min = 8, message = "A senha deve conter 8 caracteres")
	private String senha;

	@NotNull(message = "Type cannot be empty.")
	private TipoUsuario tipo; // comum ou lojista

	public UsuarioRequestDto() {
	}

	public UsuarioRequestDto(String nomeCompleto, String cpfCnpj, String email, String senha, TipoUsuario tipo) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}

	public Usuario toUsuario() { // convertendo usuarioDto em um obj usuario
		return new Usuario(null, this.getNomeCompleto(), this.getCpfCnpj(), this.getEmail(), this.getSenha(),
				this.getTipo());
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

}
