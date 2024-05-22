package com.example.sistemaBanco.service;

import static com.example.sistemaBanco.spec.TransacaoSpec.comDataMaiorOuIgualA;
import static com.example.sistemaBanco.spec.TransacaoSpec.comDataMenorOuIgualA;
import static com.example.sistemaBanco.spec.TransacaoSpec.comTipoIgual;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.sistemaBanco.dto.EmailDto;
import com.example.sistemaBanco.dto.request.GetTransacao;
import com.example.sistemaBanco.entities.Conta;
import com.example.sistemaBanco.entities.Transacao;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.TipoTransacao;
import com.example.sistemaBanco.entities.enums.TipoUsuario;
import com.example.sistemaBanco.repository.ContaRepository;
import com.example.sistemaBanco.repository.TransacaoRepository;
import com.example.sistemaBanco.service.exceptions.ContaDestinoException;
import com.example.sistemaBanco.service.exceptions.ContaOrigemException;
import com.example.sistemaBanco.service.exceptions.ContasIguaisException;
import com.example.sistemaBanco.service.exceptions.SaldoInsuficienteException;
import com.example.sistemaBanco.service.exceptions.TransferenciaNotFoundException;
import com.example.sistemaBanco.service.exceptions.UsuarioLojistaException;
import com.example.sistemaBanco.service.exceptions.ValorInvalidoException;

import jakarta.transaction.Transactional;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	ContaService contaService;

	public Page<Transacao> listarHistoricoTransacao(LocalDate dataInicio, LocalDate dataFim, TipoTransacao tipo,
			Pageable pageable) {

		Specification<Transacao> spec = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

		if (dataInicio != null)
			spec = spec.and(comDataMaiorOuIgualA(dataInicio));

		if (dataFim != null)
			spec = spec.and(comDataMenorOuIgualA(dataFim));

		if (tipo != null)
			spec = spec.and(comTipoIgual(tipo));

		return transacaoRepository.findAll(spec, pageable);
	}

	// para pegar a transferencia por id
	public GetTransacao findById(Long id) {
		Optional<Transacao> optionalTransferencia = transacaoRepository.findById(id);
		// ver se encontrou
		Transacao transferencia = optionalTransferencia.orElseThrow(() -> new TransferenciaNotFoundException(id));
		// mapeando a transferencia para o get
		GetTransacao getTransferencia = GetTransacao.fromTransferencia(transferencia);
		return getTransferencia;
	}

	@Transactional
	public void realizarSaque(Transacao saque, Conta contaOrigem) {

		// pegue o valor do saque que é um bigDecimal e compare com outro bigDecimal no valor zero  
		if (saque.getValor().compareTo(BigDecimal.ZERO)<= 0) { // se o valoe do saque for menor ou = a zero lança esse erro
			throw new ValorInvalidoException("O valor da transação deve ser positivo.");
		}
		// se o saldo da conta for maior qu eo valor do saque ai faz a operação
		if (contaOrigem.getSaldo().compareTo(saque.getValor()) >= 0) {
			contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(saque.getValor())); // pega o saldo e subtrai do valor sacado
			contaRepository.save(contaOrigem); // salva a conta

			saque.setTipo(TipoTransacao.SAQUE);
			Date dataAtual = new Date();
			saque.setData(dataAtual);

			transacaoRepository.save(saque); // salva a transacao
		} else {
			throw new SaldoInsuficienteException("Saldo insuficiente para realizar a transação."); // se n, gera esse
																									// erro
		}
	}

	@Transactional
	public void realizarSaque(Transacao saque) {
		Conta contaOrigem = contaRepository.findById(saque.getContaOrigem().getId()) // vendo se a conta origem existe
				.orElseThrow(() -> new ContaOrigemException("Conta de origem não encontrada.")); // se n gera esse erro

		realizarSaque(saque, contaOrigem);
	}

	@Transactional
	public void realizarDeposito(Transacao deposito, Conta contaDestino) {

		if (deposito.getValor().compareTo(BigDecimal.ZERO) <= 0) { // vendo se o valor do deposito é positivo
			throw new ValorInvalidoException("O valor do depósito deve ser um valor positivo.");
		}

		contaDestino.setSaldo(contaDestino.getSaldo().add(deposito.getValor())); // se for peha a conta destino e soma com
																				// o novo valor depositado
		contaRepository.save(contaDestino);

		deposito.setTipo(TipoTransacao.DEPOSITO);
		Date dataAtual = new Date();
		deposito.setData(dataAtual);

		transacaoRepository.save(deposito);
	}

	@Transactional
	public void realizarDeposito(Transacao deposito) {
		Conta contaDestino = contaRepository.findById(deposito.getContaDestino().getId()) // v se a conta que vai ser								// depositada existe
				.orElseThrow(() -> new ContaDestinoException("Conta de destino não encontrada.")); // se n encontrar
																									// gera esse erro

		realizarDeposito(deposito, contaDestino);
	}

	@Transactional
	public void validarTransferencia(Transacao transferencia, Conta contaOrigem) {
		if (transferencia.getContaOrigem().getId().equals(transferencia.getContaDestino().getId())) {
			throw new ContasIguaisException("A conta de origem é igual à conta de destino");
		}

		Usuario usuarioOrigem = contaOrigem.getUsuario();
		if (usuarioOrigem.getTipo() == TipoUsuario.LOJISTA) {
			throw new UsuarioLojistaException("Usuário de tipo lojista não pode fazer transferências");
		}
	}

	@Transactional
	public void enviarEmailTransferencia(Transacao transferencia, Conta contaDestino) {
		String destinatario = contaDestino.getUsuario().getEmail();
		String assunto = "Transferência Recebida";
		String corpo = "Você recebeu uma transferência no valor de " + transferencia.getValor() + " reais.";
		EmailDto emailDto = new EmailDto(destinatario, assunto, corpo);
		emailService.enviarEmail(emailDto);
	}

	@Transactional
	public void realizarTransferencia(Transacao transferencia) { // gera esse erro

		Conta contaOrigem = contaService.findById(transferencia.getContaOrigem().getId());
		Conta contaDestino = contaService.findById(transferencia.getContaDestino().getId());

		validarTransferencia(transferencia, contaOrigem);

		try {
			realizarSaque(transferencia, contaOrigem); // chamando metodo sauqe
			realizarDeposito(transferencia, contaDestino); // metodo deposito

			transferencia.setTipo(TipoTransacao.TRANSFERENCIA);
			transferencia.setData(new Date());

			enviarEmailTransferencia(transferencia, contaDestino);

			// se algum dos erros que tem disponivel aparecer gera a menssagem
		} catch (ValorInvalidoException | SaldoInsuficienteException | ContaOrigemException | ContaDestinoException e) {
			throw new RuntimeException("Erro ao realizar a transferência: " + e.getMessage());
		}
	}

}
