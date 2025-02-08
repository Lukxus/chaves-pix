package com.pix.chaves.services;

import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.request.UpdateChavePixRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class RemoveChavePixAction {

    @Resource
    private ChavePixRepository repository;

    @Resource
    private ReadChavePixAction readChavePixAction;

    @Transactional
    public ChavePix removeChavePix(UUID id) {
        log.info(LogMessages.INICIANDO_INATIVACAO_CHAVE_PIX, id);

        ChavePix chavePix = readChavePixAction.findById(id);

        if (chavePix.getDataHoraInativacao() != null) {
            log.warn(LogMessages.CHAVE_PIX_JA_INATIVADA, id);
            throw new BusinessException(ErrorMessages.CHAVE_PIX_JA_INATIVADA);
        }

        chavePix.setDataHoraInativacao(LocalDateTime.now());
        chavePix = repository.save(chavePix);
        log.info(LogMessages.CHAVE_PIX_INATIVADA_COM_SUCESSO, id);
        return chavePix;
    }

}
