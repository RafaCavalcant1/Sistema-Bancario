package com.example.sistemaBanco.dto;

import java.io.Serializable;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.UsuarioTipo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDto implements Serializable { // interface marcadora que indica que a classe pode ser serializada. é
													// o processo de converter um objeto em uma sequência de bytes, que
													// pode ser armazenada em um arquivo

	private static final long serialVersionUID = 1L; // nnúmero de série padrao

	private Long id;

	@NotBlank(message = "Name cannot be empty.")
	private String nomeCompleto;

	@NotBlank(message = "Cpf/cnpj cannot be empty.")
	private String cpfCnpj;

	@NotBlank(message = "Email cannot be empty.")
	@Email(message = "Invalid e-mail format.")
	private String email;

	@NotBlank(message = "Password cannot be empty.")
	private String senha;

	@NotNull(message = "Type cannot be empty.")
	private UsuarioTipo tipo; // comum ou lojista

	public UsuarioDto() {
	}

	public UsuarioDto(Long id, String nomeCompleto, String cpfCnpj, String email, String senha, UsuarioTipo tipo) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
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

	public UsuarioTipo getTipo() {
		return tipo;
	}

	public void setTipo(UsuarioTipo tipo) {
		this.tipo = tipo;
	}

	public Usuario toUsuario() { // convertendo usuarioDto em um obj usuario
		return new Usuario(this.getId(), this.getNomeCompleto(), this.getCpfCnpj(), this.getEmail(), this.getSenha(),
				this.getTipo());
	}

	public static UsuarioDto fromUsuario(Usuario usuario) { // faz  o inverso e retorna os objetps 
		return new UsuarioDto(usuario.getId(), usuario.getNomeCompleto(), usuario.getCpfCnpj(), usuario.getEmail(),
				usuario.getSenha(), usuario.getTipo());
	}
}
