package com.example.sistemaBanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistemaBanco.client.EmailClient;
import com.example.sistemaBanco.dto.EmailDto;
import com.example.sistemaBanco.service.exceptions.EmailException;

@Service
public class EmailService {

	@Autowired
    private EmailClient emailClient;

    public void enviarEmail(EmailDto emailDto) {
        try {
        	// mudar p enviaremail 
            emailClient.email(emailDto); // chamando o post e eviar o email e enviando 
        } catch (Exception e) {
            throw new EmailException("Erro ao enviar email, "  + e.getMessage()); // se der errado lança essa exceção 
        }
    }
}
