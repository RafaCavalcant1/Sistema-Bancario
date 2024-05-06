package com.example.sistemaBanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SistemaBancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaBancoApplication.class, args);
	}

}
