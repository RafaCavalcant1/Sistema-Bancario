package com.example.sistemaBanco.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaBanco.dto.GetConta;
import com.example.sistemaBanco.service.ContaService;

@RestController
@RequestMapping(value = "/contas")
public class ContaResource {

	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public ResponseEntity<List<GetConta>>retornaConta(){
		List<GetConta> list = contaService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}") // indica que a requisição vai aceitar um ID dentro da url
	public ResponseEntity<GetConta> findById(@PathVariable Long id) {
		GetConta getConta = contaService.findById(id);
		return ResponseEntity.ok(getConta);
	}
	
	
	
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deletarConta(@PathVariable Long id) {
//		contaService.deletarConta(id);
//		return ResponseEntity.noContent().build();
//	}
}
