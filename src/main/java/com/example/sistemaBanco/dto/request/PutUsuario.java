package com.example.sistemaBanco.dto.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.TipoUsuario;

public class PutUsuario extends UsuarioRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public PutUsuario(String nomeCompleto, String cpfCnpj, String email, String senha, TipoUsuario tipo) {
		super(nomeCompleto, cpfCnpj, email, senha, tipo);
	}

	public static List<PutUsuario> fromUsuario(List<Usuario> listUsuario) {
		List<PutUsuario> putUsuarioList = new ArrayList<>();
		listUsuario.forEach(usuario -> {
			putUsuarioList.add(new PutUsuario(usuario.getNomeCompleto(), usuario.getCpfCnpj(), usuario.getEmail(),
					usuario.getSenha(), usuario.getTipo()));
		});
		return putUsuarioList;
	}
}

