package com.example.sistemaBanco.spec;

import org.springframework.data.jpa.domain.Specification;

import com.example.sistemaBanco.entities.Conta;

public class ContaSpec {

	// Specification é usada para criar especificações de consulta
	//cria uma especificação para encontrar contas com o nome de usuario parecido
	
	//root: representa a entidade principal da consulta, Conta
	//query: representa a consulta em si, permite acesso a diferentes partes da consulta, como  filtros
	//criteriaBuilder: construir as cláusulas da consulta, como where, like, equal, (são criterios do filtro) 
	public static Specification<Conta> comNomeDoUsuarioParecido(String nomeCompleto) {
		return (root, query, criteriaBuilder) -> 
				criteriaBuilder.like(criteriaBuilder.lower(root.get("usuario").get("nomeCompleto")), "%" + nomeCompleto.toLowerCase() + "%");
	// o like ele faz a busca de tudo a partir do % 
				// o lower para ignorar caracteres minusculos ou maiusculos 
	}
	
	public static Specification<Conta> comAgenciaParecida(String agencia) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("agencia"), "%" + agencia + "%");
	}
	
	public static Specification<Conta> comContaIgual(String conta) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("conta"), conta);
	}
	

}
