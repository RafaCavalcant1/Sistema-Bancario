package com.example.sistemaBanco.resources.openApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.sistemaBanco.dto.request.PostAutenticacao;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Autenticação")
public interface AutenticacaoResourceOpenApi {

	@ApiResponses(value = {
	        @ApiResponse(responseCode = "403", description = "Acesso negado")
	})
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid PostAutenticacao data);
}
