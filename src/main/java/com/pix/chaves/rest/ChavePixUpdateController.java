package com.pix.chaves.rest;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.rest.dto.ChavePixRequest;
import com.pix.chaves.rest.dto.ChavePixResponse;
import com.pix.chaves.services.ChavePixService;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/chaves-pix/update")
public class ChavePixUpdateController {

    @Resource
    private ChavePixService chavePixService;

    @PutMapping("/{id}")
    public ResponseEntity<ChavePixResponse> atualizarChavePix(@PathVariable UUID id, @RequestBody ChavePixRequest request) {
        ChavePixResponse response = chavePixService.atualizarChavePix(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
