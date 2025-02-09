package com.pix.chaves.utils.validation.validators;

import com.pix.chaves.exception.AgenciaValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.utils.validation.valid.NumeroAgencia;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumeroAgenciaValidator implements ConstraintValidator<NumeroAgencia, String> {

    @Override
    public void initialize(NumeroAgencia constraintAnnotation) {
    }

    @Override
    public boolean isValid(String numeroAgencia, ConstraintValidatorContext context) {

        if (numeroAgencia == null || numeroAgencia.trim().isEmpty()) {
            log.error(LogMessages.NUMERO_AGENCIA_VAZIO_NULO);
            throw new AgenciaValidationException(ErrorMessages.NUMERO_AGENCIA_VAZIO_NULO);
        }

        if (!numeroAgencia.matches("\\d{1,4}")) {
            log.error(LogMessages.NUMERO_AGENCIA_NUMERO_DIGITOS);
            throw new AgenciaValidationException(ErrorMessages.NUMERO_AGENCIA_NUMERO_DIGITOS);
        }

        return true;
    }
}
