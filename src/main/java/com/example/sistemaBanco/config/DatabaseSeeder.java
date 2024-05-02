package com.example.sistemaBanco.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.TipoTransacao;
import com.example.sistemaBanco.entities.enums.UsuarioTipo;
import com.example.sistemaBanco.repository.ContaRepository;
import com.example.sistemaBanco.repository.TransacaoRepository;
import com.example.sistemaBanco.repository.UsuarioRepository;
import com.example.sistemaBanco.util.Md5Util;

//uma classe especifica de configuração para o perfil de teste
//para executar quando o programa for iniciado usa o implements CommandLineRunner
@Configuration
@Profile("test")
public class DatabaseSeeder implements CommandLineRunner {
	
	@Autowired // para associar uma instancia do usuarioRepository no testeconfig e usar no BD
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;

	
	@Override// tudo que colocar dentro desse método run(ele veio do implements) vai ser executado
	public void run(String... args) throws Exception {
		//id está null pois vai ser gerado pelo BD
		Usuario u1 = new Usuario(null, "Rafaela cavalcanti", "12329912447", "rafaela@gmail.com", Md5Util.cryptography("12345678"),UsuarioTipo.COMUM);
		Usuario u2 = new Usuario(null, "Jessica Silva", "11111111111", "jessica@gmail.com", Md5Util.cryptography("12345678"),UsuarioTipo.COMUM);
		Usuario u3 = new Usuario(null, "Maria Pereira", "22222222222", "maria@gmail.com", Md5Util.cryptography("12345678"),UsuarioTipo.LOJISTA);
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Conta c1 = new Conta(null, "12", "1234", 5000.00, u1);
		Conta c2 = new Conta(null, "13", "1235", 4000.00, u2);
		Conta c3 = new Conta(null, "14", "1236", 3000.00, u3);
		
		contaRepository.saveAll(Arrays.asList(c1, c2,c3));
		
		Date dataAtual = new Date();
		Transacao t1 = new Transacao(null, c1, c2, 1000.00, dataAtual, TipoTransacao.DEPOSITO);
		Transacao t2 = new Transacao(null, c2, c1, 2000.00, dataAtual, TipoTransacao.DEPOSITO);
		
		
		transacaoRepository.saveAll(Arrays.asList(t1, t2));
		
	}
}
