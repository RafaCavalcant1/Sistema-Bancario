package com.example.sistemaBanco.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.sistemaBanco.dto.IdDto;

import lombok.Data;

@Data
public class ResponseConta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String conta;
	private String agencia;
	private BigDecimal saldo;
	private IdDto usuario;

}
