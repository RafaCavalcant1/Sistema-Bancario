package com.example.sistemaBanco.springDoc;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SpringDocConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Test API")
						.version("v1")
						.description("Sistema bancário"))
				.tags(
						Arrays.asList(
								new Tag().name("Usuário"),
								new Tag().name("Transação"),
								new Tag().name("Conta")
								)
						);
	
	}
}
