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
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Transação")
public interface TransacaoResourceOpenApi {

	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200"),
	        @ApiResponse(responseCode = "404", description = "Transação não encontrada")
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
	        @ApiResponse(responseCode = "404", description = "Transação não encontrada")
	    })
	@GetMapping("/{id}") 
	ResponseEntity<GetTransacao> findById(@PathVariable Long id);
	
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	@PostMapping("/fazer-transferencias")
	void realizarTransferencia(@RequestBody Transacao transferencia);
	
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	@PostMapping("/fazer-saques")
	void realizarSaque(@RequestBody Transacao saque);
	
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	@PostMapping("/fazer-depositos")
	void realizarDeposito(@RequestBody Transacao deposito);
}
