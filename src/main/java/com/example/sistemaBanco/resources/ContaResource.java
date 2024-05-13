package com.example.sistemaBanco.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaBanco.dto.request.GetConta;
import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.service.ContaService;

@RestController
@RequestMapping(value = "/contas")
public class ContaResource {

	@Autowired
	private ContaService contaService;

	// o @RequestParam(required = false) significa que esses parametros não são
	// obrigatorios que sejam passados
	// retorna a lista de getConta
	@GetMapping
	public ResponseEntity<Page<GetConta>> pesquisarContas(@RequestParam(required = false) String conta,
			@RequestParam(required = false) String agencia, @RequestParam(required = false) String nomeCompleto, Pageable pageable) {

		// chamando a service para pesquisar contas com base nos parâmetros de
		// requisição fornecidos e armazena na lista Conta
		// o page tem todas informações sobre a pagina , essa pagina é uma pagina de conta e eu quero transforma em uma Getconta 
	    Page<Conta> paginaContas  = contaService.pesquisarContas(conta, agencia, nomeCompleto, pageable);// serve para pegar o conteudo da pagina 
		// transformando o conteuddo da pagina  em get contas 
	    List<GetConta> getContas = paginaContas.getContent()
		                            .stream()
				                    //.map((xpto) -> GetConta.fromConta(xpto)) // usando lambda 
									// usando method reference coloca por deibaixo dos panos a mesma coisa que a lambda, so que como os dois xpto é o mesmo q entra e sai ai usa o method reference 
									.map(GetConta::fromConta) // o map é para transformar que no caso to transformando a lista e comta em uma lista de GetConta
									.toList();
// criando uma nova pagina passando o conteudo r=transformados, junto com as informações da paginaçãi 
	    // o pageImpl implmenta a interfae Page 
	    // totalements é tp, mesmo que a pagina r=tenha 10 elementos e o total seja 1000, o total é 1000
		return ResponseEntity.ok(new PageImpl<>(getContas, pageable, paginaContas.getTotalElements()));
	}
	
	//return paginaAssembler.toPage(content, pageable, produtos.getTotalElements());
	
//	GetConta apply(Conta conta) {
//		return GetConta.fromConta(conta);
//	}

	@GetMapping("/{id}") // indica que a requisição vai aceitar um ID dentro da url
	public ResponseEntity<GetConta> findById(@PathVariable Long id) {
		Conta conta = contaService.findById(id);
		GetConta getConta = GetConta.fromConta(conta); // convertendo
		return ResponseEntity.ok(getConta);
	}

}
