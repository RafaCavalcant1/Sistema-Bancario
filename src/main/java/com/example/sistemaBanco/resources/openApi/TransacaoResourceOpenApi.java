package com.example.sistemaBanco.resources.openApi;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.sistemaBanco.dto.request.GetTransacao;
import com.example.sistemaBanco.dto.request.PostTransacaoDeposito;
import com.example.sistemaBanco.dto.request.PostTransacaoSaque;
import com.example.sistemaBanco.dto.request.PostTransacaoTransferencia;
import com.example.sistemaBanco.entities.enums.TipoTransacao;
import com.example.sistemaBanco.resources.exception.StandardError;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Transação")
public interface TransacaoResourceOpenApi {

	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200"),
	        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = StandardError.class)))
	    })
	@Parameter(in = ParameterIn.QUERY, name = "page", schema = @Schema(type = "integer"))
	@Parameter(in = ParameterIn.QUERY, name = "size", schema = @Schema(type = "integer"))
	@Parameter(in = ParameterIn.QUERY, name = "sort") // o sort por si ja é string 
	@GetMapping
	ResponseEntity<Page<GetTransacao>> obterHistoricoTransacao(@RequestParam(required = false)@DateTimeFormat(iso = ISO.DATE) LocalDate dataInicio,
													  @RequestParam(required = false)@DateTimeFormat(iso = ISO.DATE) LocalDate dataFim,
													  @RequestParam(required = false) TipoTransacao tipo,
													  @Parameter(hidden = true) Pageable pageable);
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200"),
	        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = StandardError.class)))
	    })
	@GetMapping("/{id}") 
	ResponseEntity<GetTransacao> findById(@PathVariable Long id);
	
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "422", description = "Entidade não processável", content = @Content(schema = @Schema(implementation = StandardError.class)))
	    })
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	@PostMapping("/fazer-transferencias")
	void realizarTransferencia(@RequestBody PostTransacaoTransferencia transferenciaDto);
	
	// content é um conteudo e espera uma anotação contet essa anotação a gnt define o conteudo, dentro dela tem um schmema que é uma estrutaura de uma cklasse, um jsonn, ele tb espera uma anotação shcema que o implementacio define a classe que representa a resposta em um json
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "422", description = "Entidade não processável", content = @Content(schema = @Schema(implementation = StandardError.class)))
	    })
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	@PostMapping("/fazer-saques")
	void realizarSaque(@RequestBody PostTransacaoSaque saqueDto);
	
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "422", description = "Entidade não processável", content = @Content(schema = @Schema(implementation = StandardError.class)))
	    })
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	@PostMapping("/fazer-depositos")
	void realizarDeposito(@RequestBody PostTransacaoDeposito depositoDto);
}
