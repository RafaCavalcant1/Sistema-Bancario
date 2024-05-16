package com.example.sistemaBanco.dto.request;

import java.io.Serializable;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.TipoUsuario;

public class PostUsuario extends UsuarioRequestDto implements Serializable { 
	
	private static final long serialVersionUID = 1L;


	public PostUsuario(String nomeCompleto, String cpfCnpj, String email, String senha, TipoUsuario tipo) {
		super(nomeCompleto, cpfCnpj, email, senha , tipo);
	}


	public static PostUsuario fromUsuario(Usuario usuario) { // faz  o inverso e retorna os objetps 
		return new PostUsuario(usuario.getNomeCompleto(), usuario.getCpfCnpj(), usuario.getEmail(),
				usuario.getSenha(), usuario.getTipo());
	}
}
