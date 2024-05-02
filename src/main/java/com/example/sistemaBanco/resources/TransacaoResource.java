package com.example.sistemaBanco.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaBanco.dto.GetTransacao;
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.service.TransacaoService;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoResource {

	@Autowired
	private TransacaoService transacaoService;

	@GetMapping
	public ResponseEntity<List<GetTransacao>> retornaTransferencia() {
		List<GetTransacao> list = transacaoService.findAll();
		// vai retornar todos usuários
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}") // indica que a requisição vai aceitar um ID dentro da url
	public ResponseEntity<GetTransacao> findById(@PathVariable Long id) {
		GetTransacao getTransferencia = transacaoService.findById(id);
		return ResponseEntity.ok(getTransferencia);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT) // essa anotação deixa o retorno do end point com esse status
	@PostMapping("/fazer-transferencias")
	public void realizarTransferencia(@RequestBody Transacao transferencia) {
		transacaoService.realizarTransferencia(transferencia);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT) // essa anotação deixa o retorno do end point com esse status
	@PostMapping("/fazer-saques")
	public void realizarSaque(@RequestBody Transacao saque) {
		transacaoService.realizarSaque(saque);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT) // essa anotação deixa o retorno do end point com esse status
	@PostMapping("/fazer-depositos")
	public void realizarDeposito(@RequestBody Transacao deposito) {
		transacaoService.realizarDeposito(deposito);
	}


}
