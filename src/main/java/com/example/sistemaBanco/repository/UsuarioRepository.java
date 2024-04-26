package com.example.sistemaBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaBanco.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByCpfCnpj(String cpfCnpj); // busca o usuario pelo cpf/cnpj se n tiver retorna null

	Usuario findByEmail(String email);// busca o usuario pelo email se n tiver retorna null
}
