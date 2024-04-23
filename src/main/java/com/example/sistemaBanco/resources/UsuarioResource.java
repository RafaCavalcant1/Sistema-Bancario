package com.example.sistemaBanco.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaBanco.entities.Usuario;

@RestController // recurso web
@RequestMapping(value = "/usuarios") // nome para chamar
public class UsuarioResource {

	@GetMapping
	public ResponseEntity<Usuario>findAll(){
		Usuario u = new Usuario(1L,"Rafaela","12329912447", "rafaela@gmail", "1234" );
		return ResponseEntity.ok().body(u);
	}
}
