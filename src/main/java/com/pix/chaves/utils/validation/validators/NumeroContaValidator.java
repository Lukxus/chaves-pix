package com.pix.chaves.utils.validation.validators;

import com.pix.chaves.exception.ContaValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.utils.validation.valid.NumeroConta;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumeroContaValidator implements ConstraintValidator<NumeroConta, String> {

    @Override
    public void initialize(NumeroConta constraintAnnotation) {
    }

    @Override
    public boolean isValid(String numeroConta, ConstraintValidatorContext context) {
        if (numeroConta == null || numeroConta.trim().isEmpty()) {
            throw new ContaValidationException(ErrorMessages.NUMERO_CONTA_VAZIO_NULO);
            //return false;
        }
        if (!numeroConta.matches("\\d{1,8}")) {
            throw new ContaValidationException(ErrorMessages.NUMERO_CONTA_NUMERO_DIGITOS);
            //return false;
        }

        return true;
    }
}
