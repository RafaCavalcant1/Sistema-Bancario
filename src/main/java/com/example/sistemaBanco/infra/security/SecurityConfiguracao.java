package com.example.sistemaBanco.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// aqui vou fazer a configuração do SS para tirar o padrão que ja vem, a telinha pronta
@Configuration
@EnableWebSecurity // para habilitar a config do web security, mas eu vou configurar nessa classe
public class SecurityConfiguracao {
	
	@Autowired
	SecurityFilter securityFilter;
	
	@Bean// para que o spring instancie a classe
	// a corrente de filtros que eu vou aplicar minha requisição para fazer a seguraçada aplicação, vão ser métodos 
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// nesse HttpSecurity é que vamos fazer as configurações para o SS
		return httpSecurity
				.csrf(csrf-> csrf.disable()) // para desligar essa config
				//sessionCreationPolicy para declarar a política de sessao que é STATELESS(auetnticação via token) não guarda quem ta logado 
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// declarando quais são as requisições http que eu quero que seja autorizada
				.authorizeHttpRequests(authorize -> authorize
						//aqui eu permito que qualquer pessoa pode TENTAR fazer logi
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						// qualquer request que seja do tipo post p endoint usuarios, o usuario pode ser Comum
						//.requestMatchers(HttpMethod.POST, "/usuarios").hasRole("COMUM")
						//para todo resto das requisições eu quero que seja autenticado apenas
						.anyRequest().authenticated()
						)
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	//esse método é um factory method para criar e configurar um bean do tipo AuthenticationManager
	//AuthenticationConfiguration é uma classe fornecida pelo Spring Security que tem a configuração de autenticação padrão.
	AuthenticationManager authenticationManager(AuthenticationConfiguration  authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		// BCryptPasswordEncoder classe que o SS fornece para fazer  criptografia das senhas
		return new BCryptPasswordEncoder();
	}
}
