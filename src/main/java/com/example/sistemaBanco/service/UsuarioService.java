package com.example.sistemaBanco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistemaBanco.dto.UsuarioDto;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.repository.UsuarioRepository;
import com.example.sistemaBanco.service.exceptions.DuplicateUserException;
import com.example.sistemaBanco.service.exceptions.InvalidPasswordLengthException;
import com.example.sistemaBanco.service.exceptions.ResourceNotFoundException;
import com.example.sistemaBanco.util.Md5Util;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// método para retornar todos os usuários do banco
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	// buscar o usuário pelo id
	public Usuario findById(Long id) {
		// acha o repositorio pelo ID mas essa operação retorna um obj OPTIONAL do tipo
		// USER, coloquei objeto como nome da variável
		Optional<Usuario> objeto = usuarioRepository.findById(id);
		// o orElseTrhrow ele tenta da o get, se n tiver usiario ele lança a exceção
		return objeto.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Usuario createUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = usuarioDto.toUsuario(); // convertendo 
		
		// verificando cpf
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

}
