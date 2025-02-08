package com.pix.chaves.utils.validation.validators;

import com.pix.chaves.exception.AgenciaValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.utils.validation.valid.NumeroAgencia;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumeroAgenciaValidator implements ConstraintValidator<NumeroAgencia, String> {

    @Override
    public void initialize(NumeroAgencia constraintAnnotation) {
    }

    @Override
    public boolean isValid(String numeroAgencia, ConstraintValidatorContext context) {

        if (numeroAgencia == null || numeroAgencia.trim().isEmpty()) {
            throw new AgenciaValidationException(ErrorMessages.NUMERO_AGENCIA_VAZIO_NULO);
            //return false;
        }

        if (!numeroAgencia.matches("\\d{1,4}")) {
            throw new AgenciaValidationException(ErrorMessages.NUMERO_AGENCIA_NUMERO_DIGITOS);
            //return false;
        }

        return true;
    }
}
