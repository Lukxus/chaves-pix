package com.pix.chaves.services;

import com.pix.chaves.domain.dto.ChavePixRequest;
import com.pix.chaves.domain.dto.ChavePixResponse;
import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.ResourceNotFoundException;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
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
        log.info("Iniciando criação da chave PIX: {}", request.getValorChave());

        if (repository.existsByValorChave(request.getValorChave())) {
            log.warn("Tentativa de criação de chave PIX já cadastrada: {}", request.getValorChave());
            throw new BusinessException(ErrorMessages.CHAVE_PIX_JA_CADASTRADA);
        }

        ChavePix chavePix = ChavePixMapper.toEntity(request);
        chavePix = repository.save(chavePix);

        log.info("Chave PIX criada com sucesso: ID {}", chavePix.getId());
        return ChavePixMapper.toResponse(chavePix);
    }

    @Transactional
    public ChavePixResponse atualizarChavePix(UUID id, ChavePixRequest request) {
        log.info("Iniciando atualização da chave PIX: ID {}", id);

        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Chave PIX não encontrada para atualização: ID {}", id);
                    return new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA);
                });

        if (chavePix.getDataHoraInativacao() != null) {
            log.warn("Tentativa de alteração de chave PIX inativada: ID {}", id);
            throw new BusinessException(ErrorMessages.CHAVE_PIX_INATIVADA_NAO_PODE_SER_ALTERADA);
        }

        chavePix.setTipoConta(request.getTipoConta());
        chavePix.setNumeroAgencia(request.getNumeroAgencia());
        chavePix.setNumeroConta(request.getNumeroConta());
        chavePix.setNomeCorrentista(request.getNomeCorrentista());
        chavePix.setSobrenomeCorrentista(request.getSobrenomeCorrentista());

        repository.save(chavePix);
        log.info("Chave PIX atualizada com sucesso: ID {}", id);
        return ChavePixMapper.toResponse(chavePix);
    }

    @Transactional
    public void inativarChavePix(UUID id) {
        log.info("Iniciando inativação da chave PIX: ID {}", id);

        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tentativa de inativação de chave PIX não encontrada: ID {}", id);
                    return new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA);
                });

        if (chavePix.getDataHoraInativacao() != null) {
            log.warn("Chave PIX já inativada: ID {}", id);
            throw new BusinessException(ErrorMessages.CHAVE_PIX_JA_INATIVADA);
        }

        chavePix.setDataHoraInativacao(LocalDateTime.now());
        repository.save(chavePix);
        log.info("Chave PIX inativada com sucesso: ID {}", id);
    }

    @Transactional
    public ChavePixResponse consultarChavePorId(UUID id) {
        log.info("Consultando chave PIX: ID {}", id);

        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Chave PIX não encontrada: ID {}", id);
                    return new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA);
                });

        log.info("Chave PIX encontrada: ID {}", id);
        return ChavePixMapper.toResponse(chavePix);
    }

    @Transactional
    public List<ChavePixResponse> consultarChavePorAgenciaConta(String conta, String agencia) {
        log.info("Consultando chaves PIX para agência {} e conta {}", agencia, conta);

        List<ChavePixResponse> chaves = repository.findByNumeroAgenciaAndNumeroConta(agencia, conta)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info("Consulta concluída. Total de chaves encontradas: {}", chaves.size());
        return chaves;
    }

    @Transactional
    public List<ChavePixResponse> consultarChavePorTipoChave(TipoChave tipoChave) {
        log.info("Consultando chaves PIX do tipo: {}", tipoChave);

        List<ChavePixResponse> chaves = repository.findByTipoChave(tipoChave)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info("Consulta concluída. Total de chaves encontradas: {}", chaves.size());
        return chaves;
    }

    @Transactional
    public List<ChavePixResponse> consultarChavePorNomeCorrentista(String nomeCorrentista, String sobrenomeCorrentista) {
        log.info("Iniciando consulta de chaves PIX para: {} {}", nomeCorrentista, sobrenomeCorrentista);

//        if (StringUtils.isBlank(nomeCorrentista) && StringUtils.isBlank(sobrenomeCorrentista)) {
//            log.warn("Consulta não realizada. Nome e sobrenome do correntista não podem estar vazios.");
//            return Collections.emptyList();
//        }

        try {
            List<ChavePixResponse> chaves = Optional.ofNullable(repository.
                            findByNomeCorrentistaContainingIgnoreCaseAndSobrenomeCorrentistaContainingIgnoreCase
                                    (nomeCorrentista, sobrenomeCorrentista))
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(ChavePixMapper::toResponse)
                    .collect(Collectors.toList());

            log.info("Consulta concluída. Total de chaves encontradas: {}", chaves.size());
            return chaves;
        } catch (Exception e) {
            log.error("Erro ao consultar chaves PIX para: {} {}. Erro: {}", nomeCorrentista, sobrenomeCorrentista, e.getMessage(), e);
            throw new RuntimeException("Erro ao consultar chaves PIX. Tente novamente mais tarde.");
        }
    }

    @Transactional
    public List<ChavePixResponse> consultarChavePorDataInclusao(LocalDateTime dataInclusaoInicio, LocalDateTime dataInclusaoFinal) {
        log.info("Consultando chaves PIX por período de inclusão: {} até {}", dataInclusaoInicio, dataInclusaoFinal);

        List<ChavePixResponse> chaves = repository.findByDataHoraInclusaoBetween(dataInclusaoInicio, dataInclusaoFinal)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info("Consulta concluída. Total de chaves encontradas: {}", chaves.size());
        return chaves;
    }

    @Transactional
    public List<ChavePixResponse> consultarChavePorDataExclusao(LocalDateTime dataExclusaoInicio, LocalDateTime dataExclusaoFinal) {
        log.info("Consultando chaves PIX por período de exclusão: {} até {}", dataExclusaoInicio, dataExclusaoFinal);

        List<ChavePixResponse> chaves = repository.findByDataHoraInativacaoBetween(dataExclusaoInicio, dataExclusaoFinal)
                .stream()
                .map(ChavePixMapper::toResponse)
                .collect(Collectors.toList());

        log.info("Consulta concluída. Total de chaves encontradas: {}", chaves.size());
        return chaves;
    }
}
