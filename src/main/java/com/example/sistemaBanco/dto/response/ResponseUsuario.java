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
	
	public static ResponseUsuario toResponseUsuario(Usuario usuario) {
        ResponseUsuario responseUsuario = new ResponseUsuario();
        BeanUtils.copyProperties(usuario, responseUsuario, "password");
        return responseUsuario;
    }
	
}
