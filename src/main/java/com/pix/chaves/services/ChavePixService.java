package com.pix.chaves.services;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.exception.ResourceNotFoundException;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.ChavePixRequest;
import com.pix.chaves.rest.dto.ChavePixResponse;
import com.pix.chaves.utils.validation.valid.ChavePixValidator;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChavePixService {

    @Resource
    private ChavePixRepository repository;

    @Resource
    private ChavePixMapper chavePixMapper;

    @Transactional
    public ChavePixResponse criarChavePix(ChavePixRequest request) {
        log.info(LogMessages.INICIANDO_CRIACAO_CHAVE_PIX, request.getValorChave());

        ChavePixValidator validator = ChavePixValidatorFactory.getValidator(request.getTipoChave());
        validator.validate(request.getValorChave());

        if (repository.existsByValorChave(request.getValorChave())) {
            log.warn(LogMessages.CHAVE_PIX_JA_CADASTRADA, request.getValorChave());
            throw new BusinessException(ErrorMessages.CHAVE_PIX_JA_CADASTRADA);
        }

        ChavePix chavePix = ChavePixMapper.toEntity(request);
        chavePix = repository.save(chavePix);

        log.info(LogMessages.CHAVE_PIX_CRIADA_COM_SUCESSO, chavePix.getId());
        return ChavePixMapper.toResponse(chavePix);
    }

    @Transactional
    public ChavePixResponse atualizarChavePix(UUID id, ChavePixRequest request) {
        log.info(LogMessages.INICIANDO_ATUALIZACAO_CHAVE_PIX, id);

        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LogMessages.CHAVE_PIX_NAO_ENCONTRADA, id);
                    return new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA);
                });

        if (chavePix.getDataHoraInativacao() != null) {
            log.warn(LogMessages.CHAVE_PIX_INATIVADA, id);
            throw new BusinessException(ErrorMessages.CHAVE_PIX_INATIVADA_NAO_PODE_SER_ALTERADA);
        }

        chavePix.setTipoConta(request.getTipoConta());
        chavePix.setNumeroAgencia(request.getNumeroAgencia());
        chavePix.setNumeroConta(request.getNumeroConta());
        chavePix.setNomeCorrentista(request.getNomeCorrentista());
        chavePix.setSobrenomeCorrentista(request.getSobrenomeCorrentista());

        repository.save(chavePix);
        log.info(LogMessages.CHAVE_PIX_ALTERADA_COM_SUCESSO, id);
        return ChavePixMapper.toResponse(chavePix);
    }

    @Transactional
    public void inativarChavePix(UUID id) {
        log.info(LogMessages.INICIANDO_INATIVACAO_CHAVE_PIX, id);

        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LogMessages.CHAVE_PIX_NAO_ENCONTRADA_INATIVACAO, id);
                    return new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA);
                });

        if (chavePix.getDataHoraInativacao() != null) {
            log.warn(LogMessages.CHAVE_PIX_JA_INATIVADA, id);
            throw new BusinessException(ErrorMessages.CHAVE_PIX_JA_INATIVADA);
        }

        chavePix.setDataHoraInativacao(LocalDateTime.now());
        repository.save(chavePix);
        log.info(LogMessages.CHAVE_PIX_INATIVADA_COM_SUCESSO, id);
    }

    @Transactional
    public ChavePixResponse consultarChavePorId(UUID id) {
        log.info(LogMessages.CONSULTANDO_CHAVE_PIX, id);

        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LogMessages.CHAVE_PIX_NAO_ENCONTRADA, id);
                    return new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA);
                });

        log.info(LogMessages.CHAVE_PIX_ENCONTRADA, id);
        return ChavePixMapper.toResponse(chavePix);
    }

    @Transactional
    public List<ChavePixResponse> consultarChavePorAgenciaConta(String conta, String agencia) {
        log.info(LogMessages.CONSULTANDO_CHAVES_POR_AGENCIA_CONTA, agencia, conta);

        List<ChavePixResponse> chaves = repository.findByNumeroAgenciaAndNumeroConta(agencia, conta)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info(LogMessages.CONSULTA_CONCLUIDA_TOTAL_CHAVES, chaves.size());
        return chaves;
    }

    @Transactional
    public List<ChavePixResponse> consultarChavePorTipoChave(TipoChave tipoChave) {
        log.info(LogMessages.CONSULTANDO_CHAVES_POR_TIPO, tipoChave);

        List<ChavePixResponse> chaves = repository.findByTipoChave(tipoChave)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info(LogMessages.CONSULTA_CONCLUIDA_TOTAL_CHAVES, chaves.size());
        return chaves;
    }

    @Transactional
    public List<ChavePixResponse> consultarChavePorNomeCorrentista(String nomeCorrentista, String sobrenomeCorrentista) {
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
    public List<ChavePixResponse> consultarChavePorDataInclusao(LocalDateTime dataInclusaoInicio, LocalDateTime dataInclusaoFinal) {
        log.info(LogMessages.CONSULTANDO_CHAVES_POR_PERIODO_INCLUSAO, dataInclusaoInicio, dataInclusaoFinal);

        List<ChavePixResponse> chaves = repository.findByDataHoraInclusaoBetween(dataInclusaoInicio, dataInclusaoFinal)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info(LogMessages.CONSULTA_CONCLUIDA_TOTAL_CHAVES, chaves.size());
        return chaves;
    }

    @Transactional
    public List<ChavePixResponse> consultarChavePorDataExclusao(LocalDateTime dataExclusaoInicio, LocalDateTime dataExclusaoFinal) {
        log.info(LogMessages.CONSULTANDO_CHAVES_POR_PERIODO_EXCLUSAO, dataExclusaoInicio, dataExclusaoFinal);

        List<ChavePixResponse> chaves = repository.findByDataHoraInativacaoBetween(dataExclusaoInicio, dataExclusaoFinal)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info(LogMessages.CONSULTA_CONCLUIDA_TOTAL_CHAVES, chaves.size());
        return chaves;
    }

    public boolean chaveJaExiste(String valorChave) {
        return repository.findByValorChave(valorChave).isPresent();
    }
}
