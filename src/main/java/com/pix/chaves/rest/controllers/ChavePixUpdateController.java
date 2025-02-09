package com.pix.chaves.rest.controllers;

import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.rest.dto.request.UpdateChavePixRequest;
import com.pix.chaves.services.chavesPix.ChavePixService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chaves-pix/update")
public class ChavePixUpdateController {

    @Resource
    private ChavePixService chavePixService;

    @PutMapping
    public ResponseEntity<ChavePixResponse> atualizarChavePix(@RequestBody UpdateChavePixRequest request) {
        ChavePixResponse response = chavePixService.atualizarChavePix(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
