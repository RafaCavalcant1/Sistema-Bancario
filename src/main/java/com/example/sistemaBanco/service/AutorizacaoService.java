package com.example.sistemaBanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sistemaBanco.dto.response.ResponseUsuarioDetails;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.repository.UsuarioRepository;

@Service
public class AutorizacaoService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	// toda vez que alguém tentar se autenticar na aplicação o SS tem que ter alguma forma de consultar esses usuários
	// então nesse método vamos fazer a consulta dos usuarios para o SS
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 Usuario usuario = usuarioRepository.findByEmail(email);
		 return ResponseUsuarioDetails.fromUsuario(usuario);
	}
	
	// esse retono é de um usuario e eu converto 

}
