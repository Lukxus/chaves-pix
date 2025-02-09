package com.pix.chaves.utils.validation.validators;

import com.pix.chaves.exception.ChavePixValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.utils.validation.valid.ChavePixValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailValidator implements ChavePixValidator {

    @Override
    public void validate(String valor) throws ChavePixValidationException {
        if (valor == null) {
            log.error(LogMessages.CHAVE_EMAIL_NULO);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_EMAIL_NULO);
        }
        if (!valor.contains("@")) {
            log.error(LogMessages.CHAVE_EMAIL_CARACTER_AT);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_EMAIL_CARACTER_AT);
        }
        if (valor.length() > 77) {
            log.error(LogMessages.CHAVE_EMAIL_NUMERO_MAXIMO_CARACTERES);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_EMAIL_NUMERO_MAXIMO_CARACTERES);
        }
    }
}
