package com.example.sistemaBanco.resources.openApi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sistemaBanco.dto.request.PostUsuario;
import com.example.sistemaBanco.dto.request.PutUsuario;
import com.example.sistemaBanco.dto.response.ResponseUsuario;
import com.example.sistemaBanco.resources.exception.StandardError;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuário")
public interface UsuarioResourceOpenApi {

	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "403",description = "Acesso negado")
	})
	
	// esta criando um parametro que vai na url ParameterIn.QUERY, o name é o nome do parametro e o @Schema define o tipo do parametro 
	@Parameter(in = ParameterIn.QUERY, name = "page", schema = @Schema(type = "integer"))
	@Parameter(in = ParameterIn.QUERY, name = "size", schema = @Schema(type = "integer"))
	@Parameter(in = ParameterIn.QUERY, name = "sort") // o sort por si ja é string 
	@GetMapping
	ResponseEntity<Page<ResponseUsuario>> pesquisarUsuario(@RequestParam(required = false) String cpfCnpj,
			@RequestParam(required = false) String email, @RequestParam(required = false) String nomeCompleto,
			@Parameter(hidden = true) Pageable pageable);
	// hidden está escondendo para não aparecer mais na documentação 
	

	
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "403",description = "Acesso negado")
	})
	@GetMapping("/{id}")
	ResponseEntity<ResponseUsuario> findById(@PathVariable Long id);

	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201"),
	        @ApiResponse(responseCode = "422", description = "Entidade não processável", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "403",description = "Acesso negado")
	})
	@PostMapping
	ResponseEntity<ResponseUsuario> insert(@RequestBody @Valid PostUsuario postUsuario);

	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200"),
	        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "422", description = "Entidade não processável", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "403",description = "Acesso negado")
	})
	@PutMapping("/{id}")
	ResponseEntity<ResponseUsuario> update(@PathVariable Long id, @RequestBody @Valid PutUsuario obj);

	@ApiResponses(value = {
	        @ApiResponse(responseCode = "204"),
	        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "400", description = "Usuário possui dependências", content = @Content(schema = @Schema(implementation = StandardError.class))),
	        @ApiResponse(responseCode = "403",description = "Acesso negado")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deletarUsuario(@PathVariable Long id);
	
}
