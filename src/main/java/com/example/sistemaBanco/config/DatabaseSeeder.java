package com.example.sistemaBanco.config;

import java.math.BigDecimal;
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
import com.example.sistemaBanco.entities.enums.TipoUsuario;
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

	@Override // tudo que colocar dentro desse método run(ele veio do implements) vai ser
				// executado
	public void run(String... args) throws Exception {
		Usuario u1 = new Usuario.Builder().nomeCompleto("Rafaela cavalcanti").cpfCnpj("12329912447")
				.email("rafaela@gmail.com").senha(Md5Util.cryptography("12345678")).tipo(TipoUsuario.COMUM).build();

		Usuario u2 = new Usuario.Builder().nomeCompleto("Jessica Silva").cpfCnpj("11111111111")
				.email("jessica@gmail.com").senha(Md5Util.cryptography("12345678")).tipo(TipoUsuario.COMUM).build();

		Usuario u3 = new Usuario.Builder().nomeCompleto("Maria Pereira").cpfCnpj("22222222222").email("maria@gmail.com")
				.senha(Md5Util.cryptography("12345678")).tipo(TipoUsuario.LOJISTA).build();

		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));

		Conta c1 = new Conta.Builder().agencia("12").conta("1234").saldo(new BigDecimal("5000.00")).usuario(u1).build();

		Conta c2 = new Conta.Builder().agencia("13").conta("1235").saldo(new BigDecimal("4000.00")).usuario(u2).build();

		Conta c3 = new Conta.Builder().agencia("14").conta("1236").saldo(new BigDecimal("3000.00")).usuario(u3).build();

		contaRepository.saveAll(Arrays.asList(c1, c2, c3));

		Date dataAtual = new Date();
		Transacao t1 = new Transacao.Builder().contaOrigem(c1).contaDestino(c2).valor(new BigDecimal("1000.00"))
				.data(dataAtual).tipo(TipoTransacao.DEPOSITO).build();

		Transacao t2 = new Transacao.Builder().contaOrigem(c2).contaDestino(c1).valor(new BigDecimal("2000.00"))
				.data(dataAtual).tipo(TipoTransacao.DEPOSITO).build();

		transacaoRepository.saveAll(Arrays.asList(t1, t2));

	}
}
