package com.example.sistemaBanco.resources;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaBanco.dto.IdDto;
import com.example.sistemaBanco.dto.request.GetConta;
import com.example.sistemaBanco.dto.request.PostConta;
import com.example.sistemaBanco.dto.response.ResponseConta;
import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.resources.openApi.ContaResourceOpenApi;
import com.example.sistemaBanco.service.ContaService;

@RestController
@RequestMapping(value = "/contas")
public class ContaResource implements ContaResourceOpenApi {

	@Autowired
	private ContaService contaService;

	// o @RequestParam(required = false) significa que esses parametros não são
	// obrigatorios que sejam passados
	// retorna a lista de getConta
	@GetMapping
	public ResponseEntity<Page<GetConta>> pesquisarContas(@RequestParam(required = false) String conta,
			@RequestParam(required = false) String agencia, @RequestParam(required = false) String nomeCompleto,
			Pageable pageable) {

		// chamando a service para pesquisar contas com base nos parâmetros de
		// requisição fornecidos e armazena na lista Conta
		// o page tem todas informações sobre a pagina , essa pagina é uma pagina de
		// conta e eu quero transforma em uma Getconta
		Page<Conta> paginaContas = contaService.pesquisarContas(conta, agencia, nomeCompleto, pageable);// serve para
																										// pegar o
																										// conteudo da
																										// pagina
		// transformando o conteuddo da pagina em get contas
		List<GetConta> getContas = paginaContas.getContent().stream()
				// .map((xpto) -> GetConta.fromConta(xpto)) // usando lambda
				// usando method reference coloca por deibaixo dos panos a mesma coisa que a
				// lambda, so que como os dois xpto é o mesmo q entra e sai ai usa o method
				// reference
				.map(GetConta::fromConta) // o map é para transformar que no caso to transformando a lista e comta em
											// uma lista de GetConta
				.toList();
		// criando uma nova pagina passando o conteudo transformados, junto com as
		// informações da paginação
		// o pageImpl implmenta a interfae Page
		// totalements é tp, mesmo que a pagina tenha 10 elementos e o total seja 1000,
		// o total é 1000
		return ResponseEntity.ok(new PageImpl<>(getContas, pageable, paginaContas.getTotalElements()));
	}

	@GetMapping("/{id}") // indica que a requisição vai aceitar um ID dentro da url
	public ResponseEntity<GetConta> findById(@PathVariable Long id) {
		Conta conta = contaService.findById(id);
		GetConta getConta = GetConta.fromConta(conta); // convertendo
		return ResponseEntity.ok(getConta);
	}

	@PostMapping
	public ResponseEntity<ResponseConta> criarConta(@RequestBody PostConta postConta) {
		Conta novaConta = new Conta();
		novaConta.setAgencia(postConta.getAgencia());

		Random random = new Random();
		int numeroConta = 1 + random.nextInt(3);
		novaConta.setConta(String.valueOf(numeroConta)); 

		// Converter IdDto para Usuario
		IdDto usuarioDto = postConta.getUsuario();
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDto.getId());

		novaConta.setUsuario(usuario);

		// Definir o saldo inicial como 0
		novaConta.setSaldo(BigDecimal.ZERO);

		// Salvar a nova conta usando o serviço
		Conta contaSalva = contaService.insert(novaConta);

		// Converter Conta para ResponseConta
		ResponseConta responseConta = new ResponseConta();
		responseConta.setId(contaSalva.getId());
		responseConta.setConta(contaSalva.getConta());
		responseConta.setAgencia(contaSalva.getAgencia());
		responseConta.setSaldo(contaSalva.getSaldo());

		// Criar e configurar o IdDto para o usuário
		IdDto usuarioId = new IdDto();
		usuarioId.setId(contaSalva.getUsuario().getId());
		responseConta.setUsuario(usuarioId);

		return ResponseEntity.ok(responseConta);

	}

}
