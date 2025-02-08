package com.pix.chaves.services;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.exception.ResourceNotFoundException;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ReadChavePixAction {

    @Resource
    private ChavePixRepository repository;

    @Transactional(readOnly = true)
    public ChavePix findById(UUID id) {
        log.info(LogMessages.CONSULTANDO_CHAVE_PIX, id);
        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LogMessages.CHAVE_PIX_NAO_ENCONTRADA, id);
                    return new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA);
                });

        log.info(LogMessages.CHAVE_PIX_ENCONTRADA, id);
        return chavePix;
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

    @Transactional
    public List<ChavePixResponse> findByTipoChave(TipoChave tipoChave) {
        log.info(LogMessages.CONSULTANDO_CHAVES_POR_TIPO, tipoChave);

        List<ChavePixResponse> chaves = repository.findByTipoChave(tipoChave)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info(LogMessages.CONSULTA_CONCLUIDA_TOTAL_CHAVES, chaves.size());
        return chaves;
    }

    @Transactional
    public List<ChavePixResponse> findByNomeCorrentista(String nomeCorrentista, String sobrenomeCorrentista) {
        log.info(LogMessages.CONSULTANDO_CHAVES_POR_NOME, nomeCorrentista, sobrenomeCorrentista);

        try {
            List<ChavePixResponse> chaves = Optional.ofNullable(repository
                            .findByNomeCorrentistaContainingIgnoreCaseAndSobrenomeCorrentistaContainingIgnoreCase(
                                    nomeCorrentista, sobrenomeCorrentista))
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(ChavePixMapper::toResponse)
                    .collect(Collectors.toList());

            log.info(LogMessages.CONSULTA_CONCLUIDA_TOTAL_CHAVES, chaves.size());
            return chaves;
        } catch (Exception e) {
            log.error(LogMessages.ERRO_CONSULTA_CHAVES_POR_NOME, nomeCorrentista, sobrenomeCorrentista, e.getMessage(), e);
            throw new RuntimeException(ErrorMessages.ERRO_CONSULTA_CHAVES);
        }
    }

    @Transactional
    public List<ChavePixResponse> findByDataInclusao(LocalDateTime dataInclusaoInicio, LocalDateTime dataInclusaoFinal) {
        log.info(LogMessages.CONSULTANDO_CHAVES_POR_PERIODO_INCLUSAO, dataInclusaoInicio, dataInclusaoFinal);

        List<ChavePixResponse> chaves = repository.findByDataHoraInclusaoBetween(dataInclusaoInicio, dataInclusaoFinal)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info(LogMessages.CONSULTA_CONCLUIDA_TOTAL_CHAVES, chaves.size());
        return chaves;
    }

    @Transactional
    public List<ChavePixResponse> findByDataExclusao(LocalDateTime dataExclusaoInicio, LocalDateTime dataExclusaoFinal) {
        log.info(LogMessages.CONSULTANDO_CHAVES_POR_PERIODO_EXCLUSAO, dataExclusaoInicio, dataExclusaoFinal);

        List<ChavePixResponse> chaves = repository.findByDataHoraInativacaoBetween(dataExclusaoInicio, dataExclusaoFinal)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info(LogMessages.CONSULTA_CONCLUIDA_TOTAL_CHAVES, chaves.size());
        return chaves;
    }
}
