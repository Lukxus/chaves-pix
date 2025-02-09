package com.pix.chaves.utils.validation.validators;

import com.pix.chaves.exception.ChavePixValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.utils.validation.valid.ChavePixValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CelularValidator implements ChavePixValidator {

    @Override
    public void validate(String valor) throws ChavePixValidationException {
        if (valor == null) {
            log.error(LogMessages.CHAVE_CELULAR_NULA);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CELULAR_NULA);
        }
        if (!valor.startsWith("+")) {
            log.error(LogMessages.CHAVE_CELULAR_CARACTERES_MAIS);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CELULAR_CARACTERES_MAIS);
        }
        String regex = "\\+[0-9]{1,2}[0-9]{1,3}[0-9]{9}";
        if (!valor.matches(regex)) {
            log.error(LogMessages.CHAVE_CELULAR_FORMATO_INVALIDO);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CELULAR_FORMATO_INVALIDO);
        }
    }
}
