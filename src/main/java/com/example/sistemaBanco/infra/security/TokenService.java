package com.example.sistemaBanco.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.sistemaBanco.entities.Usuario;

@Service
public class TokenService {
	// classe responsável pela geração e validação de tokens JTW

	
	// injeta o "segredo" do token que está no meu app.propities
	@Value("${api.security.token.secret}")
    private String secret;

	// método para gerar um token para o usuario 
    public String generateToken(Usuario usuario){
        try{
        	//cria um algoritmo de assinatura HMAC utilizando o segredo
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create() // criando o token 
            		//withIssuer é quem foi o emissor que criou o token, pode colocar qlqr nome
            		.withIssuer("auth-api") 
                    .withClaim("id", usuario.getId()) // claim para colocar informaçoes personalizadas 
                    .withClaim("nome", usuario.getNomeCompleto())
                    .withSubject(usuario.getEmail())// o usuario que esta recebeno esse token 
                 // o tempo de expiração do token
                    .withExpiresAt(genExpirationDate()) 
                    .sign(algorithm);// assina o token que usa o algoritmo 
            return token;
            // a exceção qu epode ser gerada JWTCreationException 
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
            // e vem essa mensagem quando der esse erro para facilitar de onde esta vindo 
        }
    }

    // deppis que gera o token é necessario ver se ele é valido(quando o usuario for usar)  
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token) // faz a validação de fato se o token é válido 
                    .getSubject(); // retorna a claim sub que é o email no caso 
        } catch (JWTVerificationException exception){ // quando o token for invalido
            return ""; // e retorn uma string vazia 
        }
    }

    //Este método gera uma data de expiração para o token, que é duas horas a partir do momento atual
    private Instant genExpirationDate(){
    	// -3 é a nossa zona de tempo do horario de brasilia
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); 
    //LocalDateTime.now() pega o tempo exatamente agora,plusHours(2)adicionou 2 horas toInstant e transformou em um instante 
    }
}
