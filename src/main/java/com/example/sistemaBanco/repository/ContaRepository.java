package com.example.sistemaBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaBanco.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
