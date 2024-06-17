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
@Configuration // ela serve beans para o spring 
@EnableWebSecurity // para habilitar a config do web security, mas eu vou configurar nessa classe
public class SecurityConfiguracao {

	@Autowired
	SecurityFilter securityFilter;

	
	@SuppressWarnings("removal")
	@Bean // para que o spring instancie a classe
	// a corrente de filtros que eu vou aplicar minha requisição para fazer a
	// seguraçada aplicação, vão ser métodos
	// Ele recebe por parametro o HttpSecurity que tem muitos métodos para configurar a segurança da app
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// nesse HttpSecurity é que vamos fazer as configurações para o SS
		return httpSecurity.csrf(csrf -> csrf.disable()) // para desligar essa config 
				// sessionCreationPolicy para declarar a política de sessao que é
				// STATELESS(auetnticação via token) não guarda informações no servidor 
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// declarando quais são as requisições http que eu quero que seja autorizada
				.authorizeHttpRequests(authorize -> authorize
						// aqui eu permito que qualquer pessoa pode TENTAR fazer logi
						// o requestMatchers é uma forma de selecionar URLs que vão ter regras de segurança
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						// a permissão do banco de dados 
						.requestMatchers("/h2-console/**").permitAll()
						// a permissão do swagger 
						.requestMatchers("/swagger-ui/**").permitAll() 																									// UI
						.requestMatchers("/v3/api-docs/**").permitAll()
						// qlqr usuario faz saque e deposito 
						// hasAnyRole pq v se o usuario possui pelo menos uma das roles que foi passada 
						.requestMatchers(HttpMethod.POST, "/transacoes/fazer-saques").hasAnyRole("COMUM", "LOJISTA")
						.requestMatchers(HttpMethod.POST, "/transacoes/fazer-depositos").hasAnyRole("COMUM", "LOJISTA")

						// Permitir que apenas usuários COMUM faca trnasfecia
						.requestMatchers(HttpMethod.POST, "/transacoes/fazer-transferencias").hasRole("COMUM")
						// para todo resto das requisições eu quero que seja autenticado apenas
						.anyRequest().authenticated())
				.headers(headers -> headers.frameOptions().sameOrigin()) // tive que colocar para completar a autorização do swagger 
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		// BCryptPasswordEncoder classe que o SS fornece para fazer criptografia das
		// senhas
		return new BCryptPasswordEncoder();
	}
}
