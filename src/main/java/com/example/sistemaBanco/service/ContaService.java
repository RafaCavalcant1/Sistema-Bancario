package com.example.sistemaBanco.service;

import static com.example.sistemaBanco.spec.ContaSpec.comAgenciaParecida;
import static com.example.sistemaBanco.spec.ContaSpec.comContaIgual;
import static com.example.sistemaBanco.spec.ContaSpec.comNomeDoUsuarioParecido;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.repository.ContaRepository;
import com.example.sistemaBanco.repository.UsuarioRepository;
import com.example.sistemaBanco.service.exceptions.ContaExistenteException;
import com.example.sistemaBanco.service.exceptions.ContaNotFoundException;
import com.example.sistemaBanco.service.exceptions.UsuarioNotFound;

import jakarta.transaction.Transactional;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Page<Conta> pesquisarContas( String conta, String agencia, String nomeCompleto, Pageable pegeable) {
 
		Specification<Conta>spec = Specification.where(null); 

		if(Objects.nonNull(conta)) 
			spec = spec.and(comContaIgual(conta));
		
		if(Objects.nonNull(agencia)) 
			spec = spec.and(comAgenciaParecida(agencia));
		
		if(Objects.nonNull(nomeCompleto)) 
			spec = spec.and(comNomeDoUsuarioParecido(nomeCompleto));

		return this.contaRepository.findAll(spec, pegeable);
	}

	// para pegar a conta por id
	public Conta findById(Long id) {
		return contaRepository.findById(id).orElseThrow(() -> new ContaNotFoundException(id));
	}
	
	@Transactional
	public Conta insert(Conta conta) {
		Long usuarioId = conta.getUsuario().getId();
		if (!usuarioRepository.existsById(usuarioId)) {
			throw new UsuarioNotFound("Usuário não encontrado com ID: " + usuarioId);
		}

		if (contaRepository.existsByContaAndAgencia(conta.getConta(), conta.getAgencia())) {
			throw new ContaExistenteException("Conta já existente na agência: " + conta.getAgencia());
		}
		return contaRepository.save(conta);
	}
}
