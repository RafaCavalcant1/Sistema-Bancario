package com.example.sistemaBanco.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaBanco.dto.request.GetTransacao;
import com.example.sistemaBanco.dto.request.PostTransacaoDeposito;
import com.example.sistemaBanco.dto.request.PostTransacaoSaque;
import com.example.sistemaBanco.dto.request.PostTransacaoTransferencia;
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;
import com.example.sistemaBanco.resources.openApi.TransacaoResourceOpenApi;
import com.example.sistemaBanco.service.TransacaoService;
import com.example.sistemaBanco.service.exceptions.RequisicaoInvalidaException;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoResource implements TransacaoResourceOpenApi {

	@Autowired
	private TransacaoService transacaoService;

	@GetMapping
	public ResponseEntity<Page<GetTransacao>> obterHistoricoTransacao(
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicio,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFim,
			@RequestParam(required = false) TipoTransacao tipo,
			@RequestParam(required = false) Long id,Pageable pageable) {

		// se a dataInicio é posterior a dataFim
		if ((dataInicio != null && dataFim != null) && dataInicio.isAfter(dataFim))
			throw new RequisicaoInvalidaException("Data ínicio deve ser menor ou igual a data fim");

		Page<Transacao> paginaTransacoes = transacaoService.listarHistoricoTransacao(dataInicio, dataFim, tipo, id,
				pageable);
		List<GetTransacao> getTransacoes = GetTransacao.fromTransacao(paginaTransacoes.getContent());

		return ResponseEntity.ok(new PageImpl<>(getTransacoes, pageable, paginaTransacoes.getTotalElements()));
	}

	@GetMapping("/{id}") // indica que a requisição vai aceitar um ID dentro da url
	public ResponseEntity<GetTransacao> findById(@PathVariable Long id) {
		GetTransacao getTransferencia = transacaoService.findById(id);
		return ResponseEntity.ok(getTransferencia);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT) // essa anotação deixa o retorno do end point com esse status
	@PostMapping("/fazer-transferencias")
	public void realizarTransferencia(@RequestBody PostTransacaoTransferencia transferenciaDto) {
		Transacao trasferencia = transferenciaDto.toTransacao();
		transacaoService.realizarTransferencia(trasferencia);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT) // essa anotação deixa o retorno do end point com esse status
	@PostMapping("/fazer-saques")
	public void realizarSaque(@RequestBody PostTransacaoSaque saqueDto) {
		Transacao saque = saqueDto.toTransacao();
		transacaoService.realizarSaque(saque);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT) // essa anotação deixa o retorno do end point com esse status
	@PostMapping("/fazer-depositos")
	public void realizarDeposito(@RequestBody PostTransacaoDeposito depositoDto) {
		Transacao deposito = depositoDto.toTransacao();
		transacaoService.realizarDeposito(deposito);
	}

}
