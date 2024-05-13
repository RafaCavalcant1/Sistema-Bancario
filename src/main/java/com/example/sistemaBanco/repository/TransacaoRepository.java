package com.example.sistemaBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.sistemaBanco.entities.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>, JpaSpecificationExecutor<Transacao>{

//	List<Transferencia> findByContaOrigemContaDestino(Conta contaOrigem, Conta contaDestino);
	
}
