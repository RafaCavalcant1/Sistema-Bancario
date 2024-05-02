package com.example.sistemaBanco.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.sistemaBanco.dto.PostUsuario;
import com.example.sistemaBanco.dto.PutUsuario;
import com.example.sistemaBanco.dto.response.ResponseUsuario;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.service.UsuarioService;

import jakarta.validation.Valid;

@RestController // recurso web
@RequestMapping(value = "/usuarios") // nome para chamar
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<ResponseUsuario>> retornaUsuario() {
		List<ResponseUsuario> list = usuarioService.findAll();
		// vai retornar todos usuários
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")// indica que a requisição vai aceitar um ID dentro da url
	public ResponseEntity<ResponseUsuario> findById(@PathVariable Long id) {
		ResponseUsuario getUsuario = usuarioService.findById(id);
		return ResponseEntity.ok(getUsuario);
	}
	 
	@PostMapping // inserir novo usuario
    public ResponseEntity<ResponseUsuario> insert(@RequestBody @Valid PostUsuario postUsuario) {
		Usuario usuario = postUsuario.toUsuario(); // convertendo 
		usuario = usuarioService.createUsuario(usuario); // service para criar o usuário
		ResponseUsuario responseDto = ResponseUsuario.toResponseUsuario(usuario); // converte usuario para respostadto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDto.getId()).toUri();
		return ResponseEntity.created(uri).body(responseDto); 
	}
	
//	//para atualizar o usuario
	@PutMapping(value ="/{id}")
	// a resposta é um response
	public ResponseEntity<ResponseUsuario> update(@PathVariable Long id,@RequestBody @Valid PutUsuario obj ){ //os paremetros sao: o id que vai chegar na url e vai recev=ber o obj contendo os dados para atualizar
		Usuario updatedUsuario = usuarioService.update(id, obj); // Metodo update retorna um Usuario
		ResponseUsuario responseUsuario = ResponseUsuario.toResponseUsuario(updatedUsuario); // Convertendo para ResponseUsuario
		return ResponseEntity.ok().body(responseUsuario);
	}

	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
		 usuarioService.deletarUsuario(id);
			return ResponseEntity.noContent().build();
		}

}
