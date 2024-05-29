package com.example.sistemaBanco.config;

import java.util.Collections;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration //Indica que essa classe contém configurações de beans do Spring.
public class Cors{

	@Bean //o método produz um bean que deve ser gerenciado pelo contêiner do Spring.
	//O Spring usa FilterRegistrationBean para registrar filtros personalizados no contêiner do servlet
	FilterRegistrationBean<CorsFilter>corsFilterRegistrationBean(){
		// Cria uma nova instância de CorsConfiguration para configurar as políticas de CORS
		CorsConfiguration config = new CorsConfiguration();
		
		//Define que as credenciais (como cookies, cabeçalhos de autenticação) não são permitidas em solicitações CORS.
		config.setAllowCredentials(false);
		//Permite solicitações de qualquer domínio.
		config.setAllowedOrigins(Collections.singletonList("*"));
		//Permite todos os cabeçalhos nas solicitações CORS.
		config.setAllowedHeaders(Collections.singletonList("*"));
		//Permite todos os métodos HTTP (GET, POST, PUT, DELETE, etc.).
		config.setAllowedMethods(Collections.singletonList("*"));
	
		//Cria uma instância de UrlBasedCorsConfigurationSource para armazenar a configuração de CORS.
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		// Registra a configuração de CORS para todas as rotas
		source.registerCorsConfiguration("/**", config);
	
		//Cria um FilterRegistrationBean para registrar o filtro de CORS.
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
		//Define o filtro de CORS com a configuração criada.
		bean.setFilter(new CorsFilter(source));
		//a ordem do filtro como a mais alta prioridade, garantindo que o filtro de CORS seja aplicado antes de outros filtros.
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		// Retorna o bean configurado para que ele seja gerenciado pelo contêiner do Spring
		return bean;
	}
}
