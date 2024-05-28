package com.example.sistemaBanco.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.sistemaBanco.dto.request.PostUsuario;
import com.example.sistemaBanco.dto.request.PutUsuario;
import com.example.sistemaBanco.dto.response.ResponseUsuario;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.resources.openApi.UsuarioResourceOpenApi;
import com.example.sistemaBanco.service.UsuarioService;

import jakarta.validation.Valid;

@RestController // recurso web
@RequestMapping(value = "/usuarios")
public class UsuarioResource implements UsuarioResourceOpenApi {

	@Autowired
	private UsuarioService usuarioService;

//	ResponseUsuario responseUsuario = ResponseUsuario.builder()
//        	.email("jujij")
//        	.cpfCpj("78777")
//        .build();
	@GetMapping
	public ResponseEntity<Page<ResponseUsuario>> pesquisarUsuario(@RequestParam(required = false) String cpfCnpj,
			@RequestParam(required = false) String email, @RequestParam(required = false) String nomeCompleto,
			Pageable pageable) {

		Page<Usuario> paginaUsuarios = usuarioService.pesquisarUsuario(nomeCompleto, email, cpfCnpj, pageable);
		List<ResponseUsuario> respondeUsuario = paginaUsuarios.stream().map(usuario -> { //a funcao fornecida uma lambda que aceita um parâmetro usuario e executa o bloco de código definido entre {}
            ResponseUsuario responseUsuario = new ResponseUsuario();
            // criando nova instancia de ResponseUsuario
            responseUsuario.toResponseUsuario(usuario); //chamando ométodo de instancia toResponseUsuario
            return responseUsuario;//// Retornar a instância preenchida
        })
				.toList();

		return ResponseEntity.ok(new PageImpl<>(respondeUsuario, pageable, paginaUsuarios.getTotalElements()));
	}

	@GetMapping("/{id}") // indica que a requisição vai aceitar um ID dentro da url
	public ResponseEntity<ResponseUsuario> findById(@PathVariable Long id) {
		ResponseUsuario getUsuario = usuarioService.findById(id);
		return ResponseEntity.ok(getUsuario);
	}

	@PostMapping // inserir novo usuario
	public ResponseEntity<ResponseUsuario> insert(@RequestBody @Valid PostUsuario postUsuario) {
		Usuario usuario = new Usuario();
	    BeanUtils.copyProperties(postUsuario, usuario); 
		usuario = usuarioService.createUsuario(usuario); // service para criar o usuário
		ResponseUsuario responseDto = new ResponseUsuario();
		responseDto.toResponseUsuario(usuario); //o mtodo toResponseUsuario copia as propriedades do objeto Usuario para ResponseUsuario
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDto.getId())
	            .toUri();
		return ResponseEntity.created(uri).body(responseDto);
	}

	// para atualizar o usuario
	@PutMapping(value = "/{id}")
	// a resposta é um response
	public ResponseEntity<ResponseUsuario> update(@PathVariable Long id, @RequestBody @Valid PutUsuario obj) {
		Usuario updatedUsuario = usuarioService.update(id, obj); // Metodo update retorna um Usuario
		ResponseUsuario responseDto = new ResponseUsuario();
		responseDto.toResponseUsuario(updatedUsuario);
		
		return ResponseEntity.ok().body(responseDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
		usuarioService.deletarUsuario(id);
		return ResponseEntity.noContent().build();
	}

}
