package com.example.sistemaBanco.resources.openApi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sistemaBanco.dto.request.GetConta;
import com.example.sistemaBanco.dto.request.PostConta;
import com.example.sistemaBanco.dto.response.ResponseConta;
import com.example.sistemaBanco.resources.exception.StandardError;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Conta")
public interface ContaResourceOpenApi {
	
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200"),
	        @ApiResponse(responseCode = "422", description = "Entidade não processável", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "403")
	})
	@Parameter(in = ParameterIn.QUERY, name = "page", schema = @Schema(type = "integer"))
	@Parameter(in = ParameterIn.QUERY, name = "size", schema = @Schema(type = "integer"))
	@Parameter(in = ParameterIn.QUERY, name = "sort") // o sort por si ja é string 
	@GetMapping
	public ResponseEntity<Page<GetConta>> pesquisarContas(@RequestParam(required = false) String conta,
														  @RequestParam(required = false) String agencia, 
														  @RequestParam(required = false) String nomeCompleto,
														  @Parameter(hidden = true) Pageable pageable );
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200"),
	        @ApiResponse(responseCode = "422", description = "Entidade não processável", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "403")
	})
	@GetMapping("/{id}") 
	public ResponseEntity<GetConta> findById(@PathVariable Long id);

	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200"),
	        @ApiResponse(responseCode = "422", description = "Entidade não processável", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "409", description = "Conflito", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "403")
	})
	@PostMapping
	public ResponseEntity<ResponseConta> criarConta(@RequestBody PostConta postConta);
}
