package com.example.sistemaBanco.dto.response;

import java.io.Serializable;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.UsuarioTipo;


public class ResponseUsuario implements Serializable { // interface marcadora que indica que a classe pode ser
	// serializada. é
// o processo de converter um objeto em uma sequência de bytes, que
// pode ser armazenada em um arquivo

	private static final long serialVersionUID = 1L; // nnúmero de série padrao

	private Long id;
	private String nomeCompleto;
	private String cpfCnpj;
	private String email;
	private UsuarioTipo tipo; 
	
	public ResponseUsuario() {
	}

	public ResponseUsuario(Long id, String nomeCompleto, String cpfCnpj, String email, UsuarioTipo tipo) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.tipo = tipo;
	}
	
	public static ResponseUsuario toResponseUsuario(Usuario usuario) { // convertendo usuarioDto em um obj usuario
		return new ResponseUsuario(usuario.getId(), usuario.getNomeCompleto(), usuario.getCpfCnpj(), usuario.getEmail(),
				usuario.getTipo());
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

	public UsuarioTipo getTipo() {
		return tipo;
	}

	public void setTipo(UsuarioTipo tipo) {
		this.tipo = tipo;
	}
	
	

}
