package com.pix.chaves.services.chavesPix;

import com.pix.chaves.domain.enums.TipoContaCadastro;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.request.UpdateChavePixRequest;
import com.pix.chaves.services.contaCadastro.ContaCadastroService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class UpdateChavePixAction {

    @Resource
    private ChavePixRepository repository;

    @Resource
    private ChavePixMapper chavePixMapper;

    @Resource
    private ReadChavePixAction readChavePixAction;

    @Resource
    private CreateChavePixAction createChavePixAction;

    @Resource
    private ContaCadastroService contaCadastroService;

    @Transactional
    public ChavePix atualizarChavePix(UpdateChavePixRequest request) {
        log.info(LogMessages.INICIANDO_ATUALIZACAO_CHAVE_PIX, request.getId());

        ChavePix chavePix = readChavePixAction.findById(request.getId());

        validaChaveAtiva(chavePix);

        validaNumeroChaves(request.getNumeroAgencia(), request.getNumeroConta());

        chavePix.setTipoConta(request.getTipoConta());
        chavePix.setNumeroAgencia(request.getNumeroAgencia());
        chavePix.setNumeroConta(request.getNumeroConta());
        chavePix.setNomeCorrentista(request.getNomeCorrentista());
        chavePix.setSobrenomeCorrentista(request.getSobrenomeCorrentista());

        repository.save(chavePix);
        log.info(LogMessages.CHAVE_PIX_ALTERADA_COM_SUCESSO, request.getId());
        return chavePix;
    }

    private void validaChaveAtiva(ChavePix chavePix) {
        if (chavePix.getDataHoraInativacao() != null) {
            log.warn(LogMessages.CHAVE_PIX_INATIVADA, chavePix.getId());
            throw new BusinessException(ErrorMessages.CHAVE_PIX_INATIVADA_NAO_PODE_SER_ALTERADA);
        }
    }

    private void validaNumeroChaves(String numeroAgencia, String numeroConta) {
        TipoContaCadastro tipoContaCadastro = contaCadastroService.obterTipoConta(numeroAgencia, numeroConta);
        createChavePixAction.validarNumeroChaves(numeroAgencia, numeroConta, tipoContaCadastro);
    }

}
