package com.pix.chaves.rest.controllers;

import com.pix.chaves.rest.dto.request.ChavePixFilter;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.services.chavesPix.ChavePixService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/chaves-pix/read")
public class ChavePixReadController {

    @Resource
    private ChavePixService chavePixService;

    @GetMapping("/{id}")
    public ResponseEntity<ChavePixResponse> consultarChavePorId(@PathVariable UUID id) {
        ChavePixResponse response = chavePixService.consultarChavePorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public Page<ChavePixResponse> consultarChavesPaginada(
            @Valid @ModelAttribute ChavePixFilter chavePixFilter,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<ChavePixResponse> response = chavePixService.consultarChavePaginada(chavePixFilter, pageable);
        return response;
    }

}
