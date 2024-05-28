package com.example.sistemaBanco.dto.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.TipoUsuario;

public class PostUsuario extends UsuarioRequestDto implements Serializable { 
	
	private static final long serialVersionUID = 1L;


	//construtor que recebe os dados necessários para criar um objeto PostUsuario e
	//os repassa para o construtor da classe pai (UsuarioRequestDto) usando super
	public PostUsuario(String nomeCompleto, String cpfCnpj, String email, String senha, TipoUsuario tipo) {
		super(nomeCompleto, cpfCnpj, email, senha , tipo);
	}


	//recebe um objeto Usuario como parâmetro e retorna um objeto PostUsuario
	public static List<PostUsuario> fromUsuario(List<Usuario> listUsuario) {
		List<PostUsuario> postUsuarioList = new ArrayList<>();
		listUsuario.forEach(usuario -> {
			postUsuarioList.add(new PostUsuario(usuario.getNomeCompleto(), usuario.getCpfCnpj(), usuario.getEmail(),
					usuario.getSenha(), usuario.getTipo()));
		});
		return postUsuarioList;
	}
}

