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
import com.example.sistemaBanco.resources.openApi.AutenticacaoResourceOpenApi;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AutenticacaoResource implements AutenticacaoResourceOpenApi{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	 @Autowired
	 private TokenService tokenService;
	
	//endpoint para o usuario fazer o login
	@PostMapping("/login")
	public ResponseEntity<ResponseLogin> login(@RequestBody @Valid PostAutenticacao data) {
		// usernamePass classe do SS que representa um objeto de autenticação baseado em nome de usuário e senha, elaé usada durante a autenticação para 
		//encapsular as credenciais do usuário e as informações de autenticação
		var usernamePassword = new  UsernamePasswordAuthenticationToken(data.email(), data.senha());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		//gerando um novo token que recebe um auth.getPrincipal  e pega o obj principal(quem é o usuario)
		// e faz o cast de usuario 
		var token = tokenService.generateToken( (Usuario) auth.getPrincipal());

		// retona esse token 
	        return ResponseEntity.ok(new ResponseLogin(token));
	}

}