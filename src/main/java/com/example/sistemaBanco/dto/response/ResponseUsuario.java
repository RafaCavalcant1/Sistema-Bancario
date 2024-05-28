package com.example.sistemaBanco.dto.response;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.TipoUsuario;

import lombok.Data;

@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class ResponseUsuario implements Serializable { 

	private static final long serialVersionUID = 1L; // nnúmero de série padrao

	private Long id;
	private String nomeCompleto;
	private String cpfCnpj;
	private String email;
	private TipoUsuario tipo; 
	
	// método de conversão, que chama a classe BeansUtils e o método estatico copyProperties 
	public ResponseUsuario toResponseUsuario (Usuario usuario) {
		// passa o usuario que é recebido como argumento do método, o this que é o objeto e os atributos que vão ser ignorados
		BeanUtils.copyProperties(usuario, this, "password"); // passa o usuario que é recebido como argumento do método
		return this;
	}
	
}
