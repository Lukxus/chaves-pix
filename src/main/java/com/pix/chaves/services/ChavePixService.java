package com.pix.chaves.services;

import com.pix.chaves.domain.dto.ChavePixRequest;
import com.pix.chaves.domain.dto.ChavePixResponse;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.ResourceNotFoundException;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ChavePixService {

    @Resource
    private ChavePixRepository repository;

    public ChavePixResponse criarChavePix(ChavePixRequest request) {
        if (repository.existsByValorChave(request.getValorChave())) {
            throw new BusinessException(ErrorMessages.CHAVE_PIX_JA_CADASTRADA);
        }

        ChavePix chavePix = ChavePixMapper.toEntity(request);
        repository.save(chavePix);

        return ChavePixMapper.toResponse(chavePix);
    }

    public ChavePixResponse atualizarChavePix(UUID id, ChavePixRequest request) {
        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA));

        if (chavePix.getDataHoraInativacao() != null) {
            throw new BusinessException(ErrorMessages.CHAVE_PIX_INATIVADA_NAO_PODE_SER_ALTERADA);
        }

        chavePix.setTipoConta(request.getTipoConta());
        chavePix.setNumeroAgencia(request.getNumeroAgencia());
        chavePix.setNumeroConta(request.getNumeroConta());
        chavePix.setNomeCorrentista(request.getNomeCorrentista());
        chavePix.setSobrenomeCorrentista(request.getSobrenomeCorrentista());

        repository.save(chavePix);
        return ChavePixMapper.toResponse(chavePix);
    }

    public void inativarChavePix(UUID id) {
        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA));

        if (chavePix.getDataHoraInativacao() != null) {
            throw new BusinessException(ErrorMessages.CHAVE_PIX_JA_INATIVADA);
        }

        chavePix.setDataHoraInativacao(LocalDateTime.now());
        repository.save(chavePix);
    }

    public ChavePixResponse consultarChavePorId(UUID id) {
        ChavePix chavePix = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA));

        return ChavePixMapper.toResponse(chavePix);
    }
}
