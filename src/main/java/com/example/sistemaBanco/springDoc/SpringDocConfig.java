package com.example.sistemaBanco.springDoc;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@SecurityScheme(name = "security_auth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@Configuration
public class SpringDocConfig {

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API Sistema-bancário")
						.version("v1")
						.description("Sistema bancário"))
				.tags(
						Arrays.asList(
								new Tag().name("Usuário"),
								new Tag().name("Transação"),
								new Tag().name("Conta"),
								new Tag().name("Autenticação")
								)
						);
	
	}
}
