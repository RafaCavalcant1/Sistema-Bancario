package com.example.sistemaBanco.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.sistemaBanco.dto.EmailDto;
import com.example.sistemaBanco.dto.ResponseEmailDto;

// a anotação do feignClient com o nome e a url que criei com a API 
@FeignClient(name = "email", url = "https://8rz3y.wiremockapi.cloud")
public interface EmailClient { // interface pq usa comunicação com web 
	
	// so o nome do metodo e os parametros pq é uma interface 
	@PostMapping(value = "/emails")
	ResponseEmailDto email(@RequestBody EmailDto emailDto);  // o retorno precisa ser o DTO de resposta  com 1 atributo msm
}
