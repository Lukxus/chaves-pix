package com.pix.chaves.services.chavesPix;

import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.FiltroInvalidoException;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.exception.RecursoNaoEncontradoException;
import com.pix.chaves.exception.ResourceNotFoundException;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.request.ChavePixFilter;
import com.pix.chaves.rest.dto.request.ChavePixFilterSpecification;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class ReadChavePixAction {

    @Resource
    private ChavePixRepository repository;

    @Transactional(readOnly = true)
    public ChavePix findById(UUID id) {
        log.info(LogMessages.CONSULTANDO_CHAVE_PIX, id);

        ChavePix chavePix = repository.findByIdAndDataHoraInativacaoIsNull(id)
                .orElseThrow(() -> {
                    log.warn(LogMessages.CHAVE_PIX_NAO_ENCONTRADA, id);
                    return new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA);
                });

        log.info(LogMessages.CHAVE_PIX_ENCONTRADA, id);

        return chavePix;
    }

    @Transactional(readOnly = true)
    public long countByAgenciaConta(String agencia, String conta) {
        log.info(LogMessages.CONTANDO_CHAVES_POR_AGENCIA_CONTA, agencia, conta);

        long totalChaves = repository.countByNumeroAgenciaAndNumeroContaAndDataHoraInativacaoIsNull(agencia, conta);

        log.info(LogMessages.TOTAL_CHAVES_POR_AGENCIA_CONTA, totalChaves);

        return totalChaves;
    }

    @Transactional(readOnly = true)
    public Page<ChavePixResponse> getChavesPaginada(ChavePixFilter chavePixFilter, Pageable pageable) {
        if (chavePixFilter.getDataInclusao() != null && chavePixFilter.getDataInativacao() != null) {
            throw new FiltroInvalidoException(ErrorMessages.FILTRO_DATA_INCLUSAO_E_DATA_INATIVACAO);
        }
        Specification<ChavePix> specification = new ChavePixFilterSpecification(chavePixFilter);

        log.info(LogMessages.CONSULTANDO_CHAVES_PAGINADAS);

        Page<ChavePix> pageChaves = repository.findAll(specification, pageable);
        if (pageChaves.isEmpty()) {
            throw new RecursoNaoEncontradoException(ErrorMessages.BUSCA_DE_CHAVE_VAZIA);
        }
        Page<ChavePixResponse> chaves = pageChaves
                .map(ChavePixMapper::toResponse);

        log.info(LogMessages.CONSULTA_CONCLUIDA_TOTAL_CHAVES, chaves.getTotalElements());

        return chaves;
    }

}
