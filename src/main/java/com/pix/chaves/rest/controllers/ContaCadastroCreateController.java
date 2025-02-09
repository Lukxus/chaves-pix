package com.pix.chaves.rest.controllers;

import com.pix.chaves.rest.dto.request.CreateContaRequest;
import com.pix.chaves.services.contaCadastro.ContaCadastroService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/conta/create")
public class ContaCadastroCreateController {

    @Resource
    private ContaCadastroService contaCadastroService;

    @PostMapping
    public ResponseEntity<UUID> criarConta(@RequestBody @Valid CreateContaRequest request) {
        UUID response = contaCadastroService.createContaCadastro(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
