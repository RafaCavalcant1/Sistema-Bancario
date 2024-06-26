package com.example.sistemaBanco.resources.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.sistemaBanco.service.exceptions.ContaExistenteException;
import com.example.sistemaBanco.service.exceptions.ContaDestinoException;
import com.example.sistemaBanco.service.exceptions.ContaNotFoundException;
import com.example.sistemaBanco.service.exceptions.ContaOrigemException;
import com.example.sistemaBanco.service.exceptions.ContasIguaisException;
import com.example.sistemaBanco.service.exceptions.DatabaseException;
import com.example.sistemaBanco.service.exceptions.DuplicateUserException;
import com.example.sistemaBanco.service.exceptions.EmailException;
import com.example.sistemaBanco.service.exceptions.InvalidPasswordLengthException;
import com.example.sistemaBanco.service.exceptions.RequisicaoInvalidaException;
import com.example.sistemaBanco.service.exceptions.ResourceNotFoundException;
import com.example.sistemaBanco.service.exceptions.SaldoInsuficienteException;
import com.example.sistemaBanco.service.exceptions.TransferenciaNotFoundException;
import com.example.sistemaBanco.service.exceptions.UsuarioLojistaException;
import com.example.sistemaBanco.service.exceptions.UsuarioNotFound;
import com.example.sistemaBanco.service.exceptions.ValorInvalidoException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // ele que vai intercptar as exceções que acotecerem para que o objeto possa executar o tratamento
//nessa classe que vamos dar o tratamento manual aos erros
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)//apara que o metodo seja capaz de interseptar a requisição que deu exceção para ele cair aqui
	//o tipo do obj de resposta vai ser o standardError, o nome do metodo é resourceNotFound e ele tem que receber como argumento  a exceção personalizada e depois o objeto HttpServletRequest(representa uma solicitação HTTP)
	public ResponseEntity<StandardError>resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String messageUser = "Usuário não encontrado"; // mensagem para o usuário 
		HttpStatus status = HttpStatus.NOT_FOUND;// resposta 404 que é o Not fouad
		//instanciando um obj StandardError e colocar os campos, o instante atual, o status , o erro , a menssagem que veio na exceção que ta no parametro e o caminho é o request que tb está no parametro
		StandardError err = new StandardError(Instant.now(),status.value(), messageUser, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err); // o metodo vai retornar um ResponseEntity .status é p vim com o codigo personalizado
	}
	
	// passei todas as classes de exceção
	@ExceptionHandler({InvalidPasswordLengthException.class, DuplicateUserException.class, SaldoInsuficienteException.class, ContaDestinoException.class, ContaOrigemException.class,
		ValorInvalidoException.class, ContasIguaisException.class,UsuarioLojistaException.class, ContaNotFoundException.class, UsuarioNotFound.class})
	public ResponseEntity<StandardError> handleOnlyException(Exception e, HttpServletRequest request) { // método handleCustomException que recebe Exception e (exceção lançada), e HttpServletRequest request, que representa a requisição HTTP.
	    String messageUser = e.getMessage();
	    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // o status sempre vai ser esse, 422

	    StandardError err = new StandardError(Instant.now(), status.value(), messageUser, e.getMessage(), request.getRequestURI());
	    return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> handleOnlyException(MethodArgumentNotValidException e,  HttpServletRequest request) {
		// mapa para armazenar os erros de validação, onde a chave é o nome do campo e o valor é a mensagem de erro.
		Map<String, String> errors = new HashMap<>(); 
	    e.getBindingResult().getAllErrors().forEach((error) -> {// pega todos os erros de validação da exceção MethodArgumentNotValidException e itera sobre eles.
	        String fieldName = ((FieldError) error).getField();//Para cada erro, obtém o nome do campo associado ao erro.
	        String errorMessage = error.getDefaultMessage();//Obtém a mensagem de erro padrão associada ao erro.
	        errors.put(fieldName, errorMessage);//Adiciona o nome do campo e a mensagem de erro ao mapa 
	    });
	    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; //422
	    String messageUser = "Um ou mais campos estão inválidos, faça o preenchimento correto e tente novamente";
	    String message = "Um ou mais campos estão inválidos, faça o preenchimento correto e tente novamente";
        StandardError err = new StandardError(Instant.now(), status.value(), messageUser,message, request.getRequestURI());
        
      //percorre o mapa errors que tem os campos inválidos e as mensagens de erro.
        errors.forEach((campo, mensagem) -> { 
        	err.addProblem(campo, mensagem); // aqui adiciona no metodo 
        });
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);// o metodo vai retornar um ResponseEntity .status é p vim com o codigo personalizado
	}
	
	@ExceptionHandler(DatabaseException.class)//apara que o metodo seja capaz de interseptar a requisição que deu exceção para ele cair aqui
	//o tipo do obj de resposta vai ser o standardError, o nome do metodo é database e ele tem que receber como argumento  a exceção personalizada e depois o objeto HttpServletRequest(representa uma solicitação HTTP)
	public ResponseEntity<StandardError>database(DatabaseException e, HttpServletRequest request){
		String messageUser = "Não é possível excluir o usuário, pois ele possui dependências"; // mensagem para o usuário 
		HttpStatus status = HttpStatus.BAD_REQUEST;// resposta 400 que é o Bad request
		//instanciando um obj StandardError e colocar os campos, o instante atual, o status , o erro , a menssagem que veio na exceção que ta no parametro e o caminho é o request que tb está no parametro
		StandardError err = new StandardError(Instant.now(),status.value(), messageUser, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err); // o metodo vai retornar um ResponseEntity .status é p vim com o codigo personalizado
	}
	
	@ExceptionHandler(TransferenciaNotFoundException.class)//apara que o metodo seja capaz de interseptar a requisição que deu exceção para ele cair aqui
	//o tipo do obj de resposta vai ser o standardError, o nome do metodo é resourceNotFound e ele tem que receber como argumento  a exceção personalizada e depois o objeto HttpServletRequest(representa uma solicitação HTTP)
	public ResponseEntity<StandardError>transferenciaNotFound(TransferenciaNotFoundException e, HttpServletRequest request){
		String messageUser = "Transferencia não encontrada"; // mensagem para o usuário 
		HttpStatus status = HttpStatus.NOT_FOUND;// resposta 404 que é o Not fouad
		//instanciando um obj StandardError e colocar os campos, o instante atual, o status , o erro , a menssagem que veio na exceção que ta no parametro e o caminho é o request que tb está no parametro
		StandardError err = new StandardError(Instant.now(),status.value(), messageUser, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err); // o metodo vai retornar um ResponseEntity .status é p vim com o codigo personalizado
	}
	
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<StandardError> emailException(EmailException e, HttpServletRequest request) { // método handleCustomException que recebe Exception e (exceção lançada), e HttpServletRequest request, que representa a requisição HTTP.
	    String messageUser = "Transferencia nao realizada. Serviço de envio de e-mail temporariamente indisponível";
	    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // o status sempre vai ser esse, 422

	    StandardError err = new StandardError(Instant.now(), status.value(), messageUser, e.getMessage(), request.getRequestURI());
	    return ResponseEntity.status(status).body(err);
	}
	
	// mudar p 400 
	@ExceptionHandler(RequisicaoInvalidaException.class)
	public ResponseEntity<StandardError> requisicaoInvalida(RequisicaoInvalidaException e, HttpServletRequest request) { // método handleCustomException que recebe Exception e (exceção lançada), e HttpServletRequest request, que representa a requisição HTTP.
	    String messageUser = "Data ínicio deve ser menor ou igual a data fim";
	    HttpStatus status = HttpStatus.BAD_REQUEST; // o status sempre vai ser esse, 400

	    StandardError err = new StandardError(Instant.now(), status.value(), messageUser, e.getMessage(), request.getRequestURI());
	    return ResponseEntity.status(status).body(err);
	}
	

	@ExceptionHandler(ContaExistenteException.class)
	public ResponseEntity<StandardError> requisicaoInvalida(ContaExistenteException e, HttpServletRequest request) { // método handleCustomException que recebe Exception e (exceção lançada), e HttpServletRequest request, que representa a requisição HTTP.
	    String messageUser = "Conta já existente";
	    HttpStatus status = HttpStatus.CONFLICT; // o status sempre vai ser esse, 409

	    StandardError err = new StandardError(Instant.now(), status.value(), messageUser, e.getMessage(), request.getRequestURI());
	    return ResponseEntity.status(status).body(err);
	}

}
