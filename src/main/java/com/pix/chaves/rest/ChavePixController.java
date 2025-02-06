package com.pix.chaves.rest;

import com.pix.chaves.domain.dto.ChavePixRequest;
import com.pix.chaves.domain.dto.ChavePixResponse;
import com.pix.chaves.services.ChavePixService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/chaves-pix")
public class ChavePixController {

    @Resource
    private ChavePixService chavePixService;

    @PostMapping
    public ResponseEntity<ChavePixResponse> criarChavePix(@RequestBody ChavePixRequest request) {
        ChavePixResponse response = chavePixService.criarChavePix(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChavePixResponse> atualizarChavePix(@PathVariable UUID id, @RequestBody ChavePixRequest request) {
        ChavePixResponse response = chavePixService.atualizarChavePix(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarChavePix(@PathVariable UUID id) {
        chavePixService.inativarChavePix(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChavePixResponse> consultarChavePorId(@PathVariable UUID id) {
        ChavePixResponse response = chavePixService.consultarChavePorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
