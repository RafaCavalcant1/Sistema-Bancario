package com.example.sistemaBanco.spec;

import org.springframework.data.jpa.domain.Specification;

import com.example.sistemaBanco.entities.Usuario;

public class UsuarioSpec {

	public static Specification<Usuario> comNomeParecido(String nomeCompleto) {
		return (root, query, criteriaBuilder) -> 
				criteriaBuilder.like(criteriaBuilder.lower(root.get("nomeCompleto")), "%" + nomeCompleto.toLowerCase() + "%");
	}
	
	public static Specification<Usuario> comEmailParecido(String email) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + email + "%");
	}
	
	public static Specification<Usuario> comCpfCnpjIgual(String cpfCnpj) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpfCnpj"), cpfCnpj);
	}
	

}
