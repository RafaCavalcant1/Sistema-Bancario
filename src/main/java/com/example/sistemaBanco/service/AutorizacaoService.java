package com.example.sistemaBanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sistemaBanco.repository.UsuarioRepository;

@Service
public class AutorizacaoService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	// toda vez que alguém tentar se autenticar na aplicação o SS tem que ter alguma forma de consultar esses usuários
	// então nesse método vamos fazer a consulta dos usuarios para o SS
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(username); // dessa forma o ss já é capaz de consultar os usuarios no BD
	}

}