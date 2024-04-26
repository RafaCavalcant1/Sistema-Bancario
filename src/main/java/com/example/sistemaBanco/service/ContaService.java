package com.example.sistemaBanco.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.repository.ContaRepository;

public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	// para pegar a conta por id
	public Conta buscarContaPorId(Long id) {
		return contaRepository.findById(id).orElse(null);
	}

	// para atualizar a conta
	public void atualizarConta(Conta conta) {
		contaRepository.save(conta);
	}
}
