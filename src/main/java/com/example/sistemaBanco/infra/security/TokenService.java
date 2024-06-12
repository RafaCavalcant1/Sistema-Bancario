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
                    .withIssuer("auth-api") // emissor
                    .withSubject(usuario.getEmail()) // assunto
                    .withExpiresAt(genExpirationDate()) // uma data de expiração gerada pelo método
                    .sign(algorithm);// assina o algoritmo 
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    //valida um token e retorna um assunto do token q 
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    //Este método gera uma data de expiração para o token, que é duas horas a partir do momento atual
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
