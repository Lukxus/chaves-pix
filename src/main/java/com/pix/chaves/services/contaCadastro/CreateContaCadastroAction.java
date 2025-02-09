package com.pix.chaves.services.contaCadastro;

import com.pix.chaves.domain.model.ContaCadastro;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.repository.ContaCadastroRepository;
import com.pix.chaves.rest.dto.request.CreateContaRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class CreateContaCadastroAction {

    @Resource
    private ContaCadastroRepository contaCadastroRepository;

    @Resource
    private ReadContaCadastroAction readContaCadastroAction;

    @Transactional
    public ContaCadastro createContaCadastro(CreateContaRequest createContaRequest) {
        if (readContaCadastroAction.existsByAgenciaConta(
                createContaRequest.getNumeroAgencia(),
                createContaRequest.getNumeroConta())) {
            log.warn(LogMessages.CONTA_JA_CADASTRADA,
                    createContaRequest.getNumeroAgencia(),
                    createContaRequest.getNumeroConta());
            throw new BusinessException(ErrorMessages.CONTA_JA_CADASTRADA);
        }

        ContaCadastro contaCadastro = new ContaCadastro();
        contaCadastro.setNumeroConta(createContaRequest.getNumeroConta());
        contaCadastro.setNumeroAgencia(createContaRequest.getNumeroAgencia());
        contaCadastro.setTipoContaCadastro(createContaRequest.getTipoContaCadastro());

        contaCadastro = contaCadastroRepository.save(contaCadastro);
        return contaCadastro;
    }

}
