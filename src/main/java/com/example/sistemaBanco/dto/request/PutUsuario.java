package com.example.sistemaBanco.dto.request;

import java.io.Serializable;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.UsuarioTipo;

public class PutUsuario extends UsuarioRequestDto implements Serializable { 
	
	private static final long serialVersionUID = 1L;


	public PutUsuario(String nomeCompleto, String cpfCnpj, String email, String senha, UsuarioTipo tipo) {
		super(nomeCompleto, cpfCnpj, email, senha , tipo);
	}


	public static PutUsuario fromUsuario(Usuario usuario) { // faz  o inverso e retorna os objetps 
		return new PutUsuario(usuario.getNomeCompleto(), usuario.getCpfCnpj(), usuario.getEmail(),
				usuario.getSenha(), usuario.getTipo());
	}
}
