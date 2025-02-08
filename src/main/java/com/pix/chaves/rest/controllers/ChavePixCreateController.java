package com.pix.chaves.rest.controllers;

import com.pix.chaves.rest.dto.ChavePixRequest;
import com.pix.chaves.rest.dto.ChavePixResponse;
import com.pix.chaves.services.ChavePixService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chaves-pix/create")
public class ChavePixCreateController {

    @Resource
    private ChavePixService chavePixService;

    @PostMapping
    public ResponseEntity<ChavePixResponse> criarChavePix(@RequestBody @Valid ChavePixRequest request) {
        ChavePixResponse response = chavePixService.criarChavePix(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
