package com.example.sistemaBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaBanco.entities.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

//	List<Transferencia> findByContaOrigemContaDestino(Conta contaOrigem, Conta contaDestino);
	
}
