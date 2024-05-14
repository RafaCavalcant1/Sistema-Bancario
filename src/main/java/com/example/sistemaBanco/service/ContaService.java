package com.example.sistemaBanco.service;

import static com.example.sistemaBanco.spec.ContaSpec.comAgenciaParecida;
import static com.example.sistemaBanco.spec.ContaSpec.comContaIgual;
import static com.example.sistemaBanco.spec.ContaSpec.comNomeDoUsuarioParecido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.repository.ContaRepository;
import com.example.sistemaBanco.service.exceptions.ContaNotFoundException;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	public Page<Conta> pesquisarContas( String conta, String agencia, String nomeCompleto, Pageable pegeable) {
 
		Specification<Conta> spec = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

		if (conta != null)
			spec = spec.and(comContaIgual(conta));

		if (agencia != null)
			spec = spec.and(comAgenciaParecida(agencia));

		if (nomeCompleto != null)
			spec = spec.and(comNomeDoUsuarioParecido(nomeCompleto));

		return this.contaRepository.findAll(spec, pegeable);
	}

	// para pegar a conta por id
	public Conta findById(Long id) {
		return contaRepository.findById(id).orElseThrow(() -> new ContaNotFoundException(id));
	}

	// para atualizar a conta
	public void atualizarConta(Conta conta) {
		contaRepository.save(conta);
	}

}
