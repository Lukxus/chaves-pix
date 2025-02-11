package com.pix.chaves.rest.controllers;

import com.pix.chaves.rest.dto.request.ChavePixFilter;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.request.UpdateChavePixRequest;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.rest.dto.response.ChavePixUpdateResponse;
import com.pix.chaves.services.chavesPix.ChavePixService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PostMapping("/create")
    public ResponseEntity<UUID> criarChavePix(@RequestBody @Valid CreateChavePixRequest request) {
        UUID response = chavePixService.criarChavePix(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ChavePixResponse> consultarChavePorId(@PathVariable UUID id) {
        ChavePixResponse response = chavePixService.consultarChavePorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/read")
    public Page<ChavePixResponse> consultarChavesPaginada(
            @Valid @ModelAttribute ChavePixFilter chavePixFilter,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<ChavePixResponse> response = chavePixService.consultarChavePaginada(chavePixFilter, pageable);
        return response;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ChavePixResponse> inativarChavePix(@PathVariable UUID id) {
        ChavePixResponse chavePixResponse = chavePixService.inativarChavePix(id);
        return new ResponseEntity<>(chavePixResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ChavePixUpdateResponse> atualizarChavePix(@RequestBody UpdateChavePixRequest request) {
        ChavePixUpdateResponse response = chavePixService.atualizarChavePix(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
