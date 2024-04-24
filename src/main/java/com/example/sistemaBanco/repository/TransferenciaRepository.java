package com.example.sistemaBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaBanco.entities.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{

}
