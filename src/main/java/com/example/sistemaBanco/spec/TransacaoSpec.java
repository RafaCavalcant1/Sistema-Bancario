package com.example.sistemaBanco.spec;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.enums.TipoTransacao;

public class TransacaoSpec {

	public static Specification<Transacao> dataMaiorOuIgualA(LocalDate data) {
		//criteriaBuilder.greaterThanOrEqualTo() cria uma expressão de comparação que ver se a data da transação é maior ou igual à data fornecida.
		return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("data"), data.atStartOfDay());
	//atStartOfDay pega a hora e joga para o começo do dia 00:00
	}

	public static Specification<Transacao> dataMenorOuIgualA(LocalDate data) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("data"), data.atTime(LocalTime.MAX));
		//data.atTime(LocalTime.MAX)23:59
	}
	
	public static Specification<Transacao> tipoIgual(TipoTransacao tipo) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipo"), tipo);
	}
	
	
}




