package com.example.sistemaBanco.service;

import static com.example.sistemaBanco.spec.UsuarioSpec.comEmailParecido;
import static com.example.sistemaBanco.spec.UsuarioSpec.comNomeParecido;
import static com.example.sistemaBanco.spec.UsuarioSpec.comCpfCnpjIgual;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.sistemaBanco.dto.request.PutUsuario;
import com.example.sistemaBanco.dto.response.ResponseUsuario;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.repository.UsuarioRepository;
import com.example.sistemaBanco.service.exceptions.DatabaseException;
import com.example.sistemaBanco.service.exceptions.DuplicateUserException;
import com.example.sistemaBanco.service.exceptions.InvalidPasswordLengthException;
import com.example.sistemaBanco.service.exceptions.ResourceNotFoundException;
import com.example.sistemaBanco.util.Md5Util;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// método para retornar todos os usuários do banco
	public List<ResponseUsuario> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll(); // pego a lista de usuarios 
		List<ResponseUsuario> getUsuarios = new ArrayList<>(); // uma lisra vazia que armazena os obj convertdos

		for (Usuario usuario : usuarios) { // pecorre cada obj de usuarios
			getUsuarios.add(ResponseUsuario.toResponseUsuario(usuario));  // pega a lista e adiciona os usuarios convertidos
		}

		return getUsuarios;
	}
	
	public List<Usuario> pesquisarUsuario(String nomeCompleto, String email, String cpfCnpj) {

		//estudar esse padrão 
		Specification<Usuario> spec = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

		if (cpfCnpj != null)
			spec = spec.and(comCpfCnpjIgual(cpfCnpj));

		if (email != null)
			spec = spec.and(comEmailParecido(email));

		if (nomeCompleto != null)
			spec = spec.and(comNomeParecido(nomeCompleto));

		return this.usuarioRepository.findAll(spec);
	}

	public ResponseUsuario findById(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		// ver se encontrou
		Usuario usuario = optionalUsuario.orElseThrow(() -> new ResourceNotFoundException(id)); // se não gera esse erro
		// mapeando o usuaario para o get
		ResponseUsuario getUsuario = ResponseUsuario.toResponseUsuario(usuario);
		return getUsuario;
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

		// verificando tamanho senha
		if (usuario.getSenha().length() < 8) {
			throw new InvalidPasswordLengthException("A senha deve ter no mínimo 8 caracteres.");
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

		// ver se CPF/CNPJ já está cadastrado 
		Usuario existByCpfCnpj = usuarioRepository.findByCpfCnpj(obj.getCpfCnpj());
		if (existByCpfCnpj != null) {
			throw new DuplicateUserException("CPF/CNPJ já cadastrado para outro usuário: " + obj.getCpfCnpj());
		}

		// Vver se o email já está cadastrado 
		Usuario existByEmail = usuarioRepository.findByEmail(obj.getEmail());
		if (existByEmail != null) {
			throw new DuplicateUserException("E-mail já cadastrado para outro usuário: " + obj.getEmail());
		}

		//  tamanho da senha
		if (obj.getSenha() != null && obj.getSenha().length() < 8) {
			throw new InvalidPasswordLengthException("A senha deve ter no mínimo 8 caracteres.");
		}

		// Atualizar os dados do usuário no bancp 
		updateData(entity, obj);

		return usuarioRepository.save(entity); //usuario atualizado 
	}

	// atualizar os dados do entity do que chegou com o obj
	private void updateData(Usuario entity, PutUsuario obj) {
		// se o email for diferente de nulo
		if (obj.getEmail() != null) {
			entity.setEmail(obj.getEmail()); // atualiza o email
		}
		// se o telef for diferente de nulo
		if (obj.getNomeCompleto() != null) {
			entity.setNomeCompleto(obj.getNomeCompleto()); // atualiza o telefone
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
		// vai ver se existe
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
