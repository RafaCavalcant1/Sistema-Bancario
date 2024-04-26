package com.example.sistemaBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.entities.Transferencia;
import com.example.sistemaBanco.repository.ContaRepository;
import com.example.sistemaBanco.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository transferenciaRepository;
	
	@Autowired
	private ContaRepository contaRepository;

	public List<Transferencia> findAll(){
		return transferenciaRepository.findAll();
	}

	public void transferirEntreContas(Transferencia transferencia) {
		Conta contaOrigem = contaRepository.findById(transferencia.getContaOrigem()) //vendo se a conta origem informada existe 
				.orElseThrow(() -> new RuntimeException("Conta de origem não encontrada."));  // mudar nome do erro 
		Conta contaDestino = contaRepository.findById(transferencia.getContaDestino()) //vendo se a conta destino informada existe 
				.orElseThrow(() -> new RuntimeException("Conta de destino não encontrada."));  // mudar nome do erro 
 
		// v se o saldo da conta O é suficiente 
		if (contaOrigem.getSaldo() >= transferencia.getValor()) {
			// se for atualiza o saldo da conta O
			contaOrigem.setSaldo(contaOrigem.getSaldo() - transferencia.getValor());
			// atualiza o saldo da conta D somando com o valor transferido 
			contaDestino.setSaldo(contaDestino.getSaldo() + transferencia.getValor());
			// salva no bancp 
			contaRepository.save(contaOrigem);
			contaRepository.save(contaDestino);
		} 
		// se o saldo n for sufic gera o erro 
		else {
			throw new RuntimeException("Saldo insuficiente para realizar a transferência.");  // mudar nome do erro 
		}
	}

}
