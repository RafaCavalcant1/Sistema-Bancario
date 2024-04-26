package com.example.sistemaBanco.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.sistemaBanco.dto.UsuarioDto;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.service.UsuarioService;

import jakarta.validation.Valid;

@RestController // recurso web
@RequestMapping(value = "/usuarios") // nome para chamar
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> retornaUsuario() {
		List<Usuario> list = usuarioService.findAll();
		// vai retornar todos usuários
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}") // indica que a requisição vai aceitar um ID dentro da url
	// o @PathVariable serve para o spring aceitar o id como parametro da url
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario objeto = usuarioService.findById(id);
		return ResponseEntity.ok().body(objeto);
	}
	
	@PostMapping // inserir novo usuario
    public ResponseEntity<UsuarioDto> insert(@RequestBody @Valid UsuarioDto usuarioDto) {
		Usuario usuario = usuarioService.createUsuario(usuarioDto); // service para criar o usuário
		UsuarioDto responseDto = UsuarioDto.fromUsuario(usuario); // converte usuario para respostadto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDto.getId()).toUri();
		return ResponseEntity.created(uri).body(responseDto); 

	}

}
