package com.pix.chaves.utils.validation.validators;

import com.pix.chaves.exception.ChavePixValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.utils.validation.valid.ChavePixValidator;


public class ChaveAleatoriaValidator implements ChavePixValidator {

    @Override
    public void validate(String valor) throws ChavePixValidationException {

        if (valor == null || valor.trim().isEmpty()) {
            throw new ChavePixValidationException(ErrorMessages.CHAVE_ALEATORIA_VAZIA_NULA);
        }

        if (!valor.matches("^[a-zA-Z0-9]+$")) {
            throw new ChavePixValidationException(ErrorMessages.CHAVE_ALEATORIA_ALFANUMERICOS);
        }

        if (valor.length() > 36) {
            throw new ChavePixValidationException(ErrorMessages.CHAVE_ALEATORIA_CARACTERES_MAXIMOS);
        }
    }
}
