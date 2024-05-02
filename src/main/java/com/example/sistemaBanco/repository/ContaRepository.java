package com.example.sistemaBanco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaBanco.dto.GetConta;
import com.example.sistemaBanco.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {


	Optional<GetConta> findById(Conta contaOrigem);
}
