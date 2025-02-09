package com.pix.chaves.rest.controllers;

import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.services.chavesPix.ChavePixService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/chaves-pix/remove")
public class ChavePixRemoveController {

    @Resource
    private ChavePixService chavePixService;

    @DeleteMapping("/{id}")
    public ResponseEntity<ChavePixResponse> inativarChavePix(@PathVariable UUID id) {
        ChavePixResponse chavePixResponse = chavePixService.inativarChavePix(id);
        return new ResponseEntity<>(chavePixResponse, HttpStatus.OK);
    }

}
