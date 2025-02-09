package com.pix.chaves.services.contaCadastro;

import com.pix.chaves.domain.model.ContaCadastro;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.repository.ContaCadastroRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ReadContaCadastroAction {

    @Resource
    private ContaCadastroRepository contaCadastroRepository;

    @Transactional
    public ContaCadastro findByAgenciaConta(String numeroConta, String numeroAgencia) {
        return contaCadastroRepository.findByNumeroAgenciaAndNumeroConta(
                numeroConta,
                numeroAgencia).orElseThrow(() -> {
            log.warn(LogMessages.CONTA_NAO_CADASTRADA,
                    numeroConta,
                    numeroAgencia);
            return new BusinessException(ErrorMessages.CONTA_NAO_CADASTRADA);
        });

    }

    @Transactional
    public Boolean existsByAgenciaConta(String numeroConta, String numeroAgencia) {
        return contaCadastroRepository.existsByNumeroAgenciaAndNumeroConta(
                numeroConta,
                numeroAgencia);
    }

}

