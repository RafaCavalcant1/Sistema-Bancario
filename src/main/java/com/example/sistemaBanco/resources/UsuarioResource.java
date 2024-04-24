package com.example.sistemaBanco.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.service.UsuarioService;

@RestController // recurso web
@RequestMapping(value = "/usuarios") // nome para chamar
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> retornaUsuario(){
		List<Usuario> list = usuarioService.findAll();
		// vai retornar todos usuários
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value ="/{id}") // indica que a requisição vai aceitar um ID dentro da url
	//o @PathVariable serve para o spring aceitar o id como parametro da url
	public ResponseEntity<Usuario> findById(@PathVariable Long id){
		Usuario objeto = usuarioService.findById(id);
		return ResponseEntity.ok().body(objeto);
	}
}
