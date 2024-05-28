package com.example.sistemaBanco.dto.request;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.TipoUsuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioRequestDto implements Serializable { 

	private static final long serialVersionUID = 1L; 

	@NotBlank(message = "Name cannot be empty.")
	private String nomeCompleto;

	@NotBlank(message = "Cpf/cnpj cannot be empty.")
	private String cpfCnpj;

	@NotBlank(message = "Email cannot be empty.")
	@Email(message = "Invalid e-mail format.")
	private String email;

	@NotBlank(message = "Password cannot be empty.")
	@Size(min = 8, message = "A senha deve conter 8 caracteres")
	private String senha;

	@NotNull(message = "Type cannot be empty.")
	private TipoUsuario tipo; // comum ou lojista


	public UsuarioRequestDto toUsuario(Usuario usuario) { // convertendo usuarioDto em um obj usuario
		BeanUtils.copyProperties(usuario, this);
		return this;
	}

}
