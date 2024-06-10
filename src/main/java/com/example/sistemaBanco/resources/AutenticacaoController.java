package com.example.sistemaBanco.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaBanco.dto.ResponseLogin;
import com.example.sistemaBanco.dto.request.PostAutenticacao;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	 @Autowired
	 private TokenService tokenService;
	
	//endpoint para o usuario fazer o login
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid PostAutenticacao data) {
		var usernamePassword = new  UsernamePasswordAuthenticationToken(data.email(), data.senha());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		 var token = tokenService.generateToken((Usuario) auth.getPrincipal());

	        return ResponseEntity.ok(new ResponseLogin(token));
	}

}