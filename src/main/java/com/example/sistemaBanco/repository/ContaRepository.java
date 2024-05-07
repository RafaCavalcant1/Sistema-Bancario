package com.example.sistemaBanco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.sistemaBanco.dto.request.GetConta;
import com.example.sistemaBanco.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>,JpaSpecificationExecutor<Conta> {

	Optional<GetConta> findById(Conta contaOrigem);
}
