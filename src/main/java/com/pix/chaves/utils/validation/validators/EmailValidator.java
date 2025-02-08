package com.pix.chaves.utils.validation.validators;

import com.pix.chaves.exception.ChavePixValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.utils.validation.valid.ChavePixValidator;

public class EmailValidator implements ChavePixValidator {

    @Override
    public void validate(String valor) throws ChavePixValidationException {
        if (valor == null || !valor.contains("@")) {
            throw new ChavePixValidationException(ErrorMessages.CHAVE_EMAIL_CARACTER_AT);
        }
        if (valor.length() > 77) {
            throw new ChavePixValidationException(ErrorMessages.CHAVE_EMAIL_NUMERO_MAXIMO_CARACTERES);
        }
    }
}
