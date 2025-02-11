package com.pix.chaves.services.chavesPix;

import com.pix.chaves.domain.enums.TipoContaCadastro;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.services.contaCadastro.ContaCadastroService;
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
    private ChavePixRepository chavePixRepository;

    @Resource
    private ChavePixMapper chavePixMapper;

    @Resource
    private ReadChavePixAction readChavePixAction;

    @Resource
    private ContaCadastroService contaCadastroService;

    @Transactional
    public ChavePix createChavePix(CreateChavePixRequest request) {
        log.info(LogMessages.INICIANDO_CRIACAO_CHAVE_PIX, request.getValorChave());

        ChavePixValidator validator = ChavePixValidatorFactory.getValidator(request.getTipoChave());
        validator.validate(request.getValorChave());

        if (readChavePixAction.existsByValorChave(request.getValorChave())) {
            log.warn(LogMessages.CHAVE_PIX_JA_CADASTRADA, request.getValorChave());
            throw new BusinessException(ErrorMessages.CHAVE_PIX_JA_CADASTRADA);
        }

        TipoContaCadastro tipoContaCadastro = contaCadastroService.obterTipoConta(
                request.getNumeroAgencia(),
                request.getNumeroConta()
        );

        validarNumeroChaves(request.getNumeroAgencia(), request.getNumeroConta(), tipoContaCadastro);

        ChavePix chavePix = ChavePixMapper.toEntity(request);
        chavePix = chavePixRepository.save(chavePix);

        log.info(LogMessages.CHAVE_PIX_CRIADA_COM_SUCESSO, chavePix.getId());
        return chavePix;
    }

    public void validarNumeroChaves(String numeroAgencia, String numeroConta, TipoContaCadastro tipoContaCadastro) {
        long numeroChaves = readChavePixAction.countByAgenciaConta(numeroAgencia, numeroConta);

        if (tipoContaCadastro == TipoContaCadastro.FISICA) {
            if (numeroChaves >= 5) {
                throw new BusinessException(ErrorMessages.LIMITE_CHAVES_PF);
            }
        } else if (tipoContaCadastro == TipoContaCadastro.JURIDICA) {
            if (numeroChaves >= 20) {
                throw new BusinessException(ErrorMessages.LIMITE_CHAVES_PJ);
            }
        }
    }


}
