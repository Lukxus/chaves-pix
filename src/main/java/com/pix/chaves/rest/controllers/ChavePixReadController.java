package com.pix.chaves.rest.controllers;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.services.chavesPix.ChavePixService;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
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

    @GetMapping("/tipo-chave")
    public ResponseEntity<List<ChavePixResponse>> consultarChavesPorTipoChave(@RequestParam TipoChave tipoChave) {
        List<ChavePixResponse> response = chavePixService.consultarChavePorTipoChave(tipoChave);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/agencia-conta")
    public ResponseEntity<List<ChavePixResponse>> consultarChavesPorAgenciaConta(
            @RequestParam String conta,
            @RequestParam String agencia
    ) {
        List<ChavePixResponse> response = chavePixService.consultarChavePorAgenciaConta(conta, agencia);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nome-correntista")
    public ResponseEntity<List<ChavePixResponse>> consultarChavesPorNomeCorrentista(
            @RequestParam String nomeCorrentista,
            @RequestParam String sobrenomeCorrentista
    ) {
        List<ChavePixResponse> response = chavePixService.consultarChavePorNomeCorrentista(nomeCorrentista, sobrenomeCorrentista);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/data-inclusao")
    public ResponseEntity<List<ChavePixResponse>> consultarChavesPorDataInclusao(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInclusaoInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInclusaoFinal
    ) {
        List<ChavePixResponse> response = chavePixService.consultarChavePorDataInclusao(dataInclusaoInicio, dataInclusaoFinal);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/data-exclusao")
    public ResponseEntity<List<ChavePixResponse>> consultarChavesPorDataExclusao(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataExclusaoInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataExclusaoFinal
    ) {
        List<ChavePixResponse> response = chavePixService.consultarChavePorDataExclusao(dataExclusaoInicio, dataExclusaoFinal);
        return ResponseEntity.ok(response);
    }

}
