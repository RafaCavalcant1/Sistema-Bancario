package com.example.sistemaBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.sistemaBanco.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario>{

	Usuario findByCpfCnpj(String cpfCnpj); // busca o usuario pelo cpf/cnpj se n tiver retorna null

	UserDetails findByEmail(String email);// busca o usuario pelo email se n tiver retorna null

	boolean existsByCpfCnpjAndIdNot(String cpfCnpj, Long id);

	boolean existsByEmailAndIdNot(String email, Long id);
	
	// userDatails pq vai ser usado pelo SS
	
}
