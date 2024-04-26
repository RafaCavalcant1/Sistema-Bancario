package com.example.sistemaBanco.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaBanco.entities.Transferencia;
import com.example.sistemaBanco.service.TransferenciaService;

@RestController
public class TransferenciaResource {

	@Autowired
    private TransferenciaService transferenciaService;
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT) // essa anotação deixa o retorno do end point com esse status
    @PostMapping("/fazer-transferencias")
    public void realizarTransferencia(@RequestBody Transferencia transferencia) {
        transferenciaService.transferirEntreContas(transferencia);
    }
	
}
