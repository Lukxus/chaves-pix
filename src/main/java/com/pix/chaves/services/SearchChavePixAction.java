package com.pix.chaves.services;

import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.ResourceNotFoundException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.ChavePixResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SearchChavePixAction {

    @Resource
    private ChavePixRepository repository;

    @Resource
    private ChavePixMapper chavePixMapper;

    public SearchChavePixAction(ChavePixRepository repository, ChavePixMapper chavePixMapper) {
        this.repository = repository;
        this.chavePixMapper = chavePixMapper;
    }

    @Transactional(readOnly = true)
    public ChavePixResponse findById(UUID id) {
        log.info(LogMessages.CONSULTANDO_CHAVE_PIX, id);
        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LogMessages.CHAVE_PIX_NAO_ENCONTRADA, id);
                    return new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA);
                });

        log.info(LogMessages.CHAVE_PIX_ENCONTRADA, id);
        return ChavePixMapper.toResponse(chavePix);
    }

    @Transactional(readOnly = true)
    public List<ChavePixResponse> findByAgenciaConta(String agencia, String conta) {
        log.info(LogMessages.CONSULTANDO_CHAVES_POR_AGENCIA_CONTA, agencia, conta);
        List<ChavePixResponse> chaves = repository.findByNumeroAgenciaAndNumeroConta(agencia, conta)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());
        log.info(LogMessages.CONSULTA_CONCLUIDA_TOTAL_CHAVES, chaves.size());
        return chaves;
    }

    // Outras consultas (por tipo, por nome, etc.) podem ser implementadas de forma semelhante...
}
