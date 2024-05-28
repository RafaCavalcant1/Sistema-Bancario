package com.example.sistemaBanco.service;

import static com.example.sistemaBanco.spec.UsuarioSpec.comEmailParecido;
import static com.example.sistemaBanco.spec.UsuarioSpec.comNomeParecido;
import static com.example.sistemaBanco.spec.UsuarioSpec.comCpfCnpjIgual;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.sistemaBanco.dto.request.PutUsuario;
import com.example.sistemaBanco.dto.response.ResponseUsuario;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.repository.UsuarioRepository;
import com.example.sistemaBanco.service.exceptions.DatabaseException;
import com.example.sistemaBanco.service.exceptions.DuplicateUserException;
import com.example.sistemaBanco.service.exceptions.ResourceNotFoundException;
import com.example.sistemaBanco.util.Md5Util;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Page<Usuario> pesquisarUsuario(String nomeCompleto, String email, String cpfCnpj, Pageable pageable) {

		Specification<Usuario> spec = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

		if(Objects.nonNull(cpfCnpj)) 
			spec = spec.and(comCpfCnpjIgual(cpfCnpj));

		if(Objects.nonNull(email)) 
			spec = spec.and(comEmailParecido(email));
		
		// o OBJECTS serve para fazer validações se é igua, se está nula, se não esta nula, ultil no POO 
		//ela tem métodos utilitários para operar em objetos ou verificar certas condições antes de operações. 
		if(Objects.nonNull(nomeCompleto)) 
			spec = spec.and(comNomeParecido(nomeCompleto));
			
		return this.usuarioRepository.findAll(spec, pageable);
	}

	public ResponseUsuario findById(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		// ver se encontrou
		Usuario usuario = optionalUsuario.orElseThrow(() -> new ResourceNotFoundException(id)); // se não gera esse erro
		// mapeando o usuaario para o get
		ResponseUsuario responseUsuario = new ResponseUsuario();
		responseUsuario.toResponseUsuario(usuario);
		return responseUsuario;
	}

	public Usuario createUsuario(Usuario usuario) {
		// verificando cpf
		// v se ja tem um usuario, se tiver ele vai p variavel existBy, se tiver lança o
		// erro, se n fica null e salva o usuaio
		Usuario existByCpfCnpj = usuarioRepository.findByCpfCnpj(usuario.getCpfCnpj());
		if (existByCpfCnpj != null) {
			throw new DuplicateUserException("CPF/CNPJ já cadastrado: " + usuario.getCpfCnpj());
		}

		// verificando cemail
		Usuario existByEmail = usuarioRepository.findByEmail(usuario.getEmail());
		if (existByEmail != null) {
			throw new DuplicateUserException("E-mail já cadastrado: " + usuario.getEmail());
		}

		String encryptedPassword = Md5Util.cryptography(usuario.getSenha());
		usuario.setSenha(encryptedPassword);

		return usuarioRepository.save(usuario);
	}

	// atualizar um dado do usuario
	@Transactional // para garantir que as operações sejam executadas dentro de uma transação
	public Usuario update(Long id, PutUsuario obj) {
		// buscar o usuario pelo id , se n existir gera esse erro
		Usuario entity = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

		//vendo se o email ja esta cadastrado menos o do usuario atual 
        if (cpfCnpjExcludingUsuario(id, obj.getCpfCnpj())) {
            throw new DuplicateUserException("CPF/CNPJ já cadastrado para outro usuário: " + obj.getCpfCnpj());
        }

       //vendo se o email ja esta cadastrado menos o do usuario atual 
        if (emailExcludingUsuario(id, obj.getEmail())) {
            throw new DuplicateUserException("E-mail já cadastrado para outro usuário: " + obj.getEmail());
        }

		// Atualizar os dados do usuário no bancp
		updateData(entity, obj);

		return usuarioRepository.save(entity); // usuario atualizado
	}
	
	private boolean cpfCnpjExcludingUsuario(Long id, String cpfCnpj) {
        return usuarioRepository.existsByCpfCnpjAndIdNot(cpfCnpj, id);
    }

    private boolean emailExcludingUsuario(Long id, String email) {
        return usuarioRepository.existsByEmailAndIdNot(email, id);
    }


	// atualizar os dados do entity do que chegou com o obj
	private void updateData(Usuario entity, PutUsuario obj) {
		// se o email for diferente de nulo
		if (obj.getEmail() != null) {
			entity.setEmail(obj.getEmail()); // atualiza o email
		}
		// se o telef for diferente de nulo
		if (obj.getNomeCompleto() != null) {
			entity.setNomeCompleto(obj.getNomeCompleto()); 
		}
		if (obj.getCpfCnpj() != null) {
			entity.setCpfCnpj(obj.getCpfCnpj());
		}
		if (obj.getTipo() != null) {
			entity.setTipo(obj.getTipo());
		}
		if (obj.getSenha() != null) {
			String encryptedPassword = Md5Util.cryptography(obj.getSenha());
			entity.setSenha(encryptedPassword);
			entity.setSenha(obj.getSenha());
		}

		// não vai atualizar o id
	}

	// deletar o usuario
	public void deletarUsuario(Long id) {
		// vai ver se n existe
		if (!usuarioRepository.existsById(id)) {
			throw new ResourceNotFoundException(id); // gera o erro caso n exista
		}
		try { // verificar se tem alguma conta com ele
			usuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
