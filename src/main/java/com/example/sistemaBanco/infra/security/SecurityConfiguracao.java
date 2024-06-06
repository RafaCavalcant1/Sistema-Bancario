package com.example.sistemaBanco.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// aqui vou fazer a configuração do SS para tirar o padrão que ja vem, a telinha pronta
@Configuration
@EnableWebSecurity // para habilitar a config do web security, mas eu vou configurar nessa classe
public class SecurityConfiguracao {

	@Bean// para que o spring instancie a classe
	// a corrente de filtros que eu vou aplicar minha requisição para fazer a seguraçada aplicação, vão ser métodos 
	SecurityFilterChain securityFilterChai(HttpSecurity httpSecurity) throws Exception {
		// nesse HttpSecurity é que vamos fazer as configurações para o SS
		return httpSecurity
				.csrf(csrf-> csrf.disable()) // para desligar essa config
				//sessionCreationPolicy para declarar a política de sessao que é STATELESS(auetnticação via token)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}
}
