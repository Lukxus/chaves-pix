package com.pix.chaves.services;

import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.utils.validation.ChavePixValidatorFactory;
import com.pix.chaves.utils.validation.valid.ChavePixValidator;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class CreateChavePixAction {

    @Resource
    private ChavePixRepository repository;

    @Resource
    private ChavePixMapper chavePixMapper;

    @Resource
    private ReadChavePixAction readChavePixAction;

    @Transactional
    public ChavePix createChavePix(CreateChavePixRequest request) {
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
        return chavePix;
    }

    private void validaUpdateChave(ChavePix chavePix, CreateChavePixRequest request) {
        if (chavePix.getDataHoraInativacao() != null) {
            log.warn(LogMessages.CHAVE_PIX_INATIVADA, chavePix.getId());
            throw new BusinessException(ErrorMessages.CHAVE_PIX_INATIVADA_NAO_PODE_SER_ALTERADA);
        }

    }

}
