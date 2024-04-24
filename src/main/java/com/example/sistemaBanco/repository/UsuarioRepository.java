package com.example.sistemaBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sistemaBanco.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
