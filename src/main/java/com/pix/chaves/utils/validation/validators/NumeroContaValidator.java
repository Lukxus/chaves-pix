package com.pix.chaves.utils.validation.validators;

import com.pix.chaves.exception.ContaValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.utils.validation.valid.NumeroConta;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumeroContaValidator implements ConstraintValidator<NumeroConta, String> {

    @Override
    public void initialize(NumeroConta constraintAnnotation) {
    }

    @Override
    public boolean isValid(String numeroConta, ConstraintValidatorContext context) {
        if (numeroConta == null || numeroConta.trim().isEmpty()) {
            log.error(LogMessages.NUMERO_CONTA_VAZIO_NULO);
            throw new ContaValidationException(ErrorMessages.NUMERO_CONTA_VAZIO_NULO);
        }
        if (!numeroConta.matches("\\d{1,8}")) {
            log.error(LogMessages.NUMERO_CONTA_NUMERO_DIGITOS);
            throw new ContaValidationException(ErrorMessages.NUMERO_CONTA_NUMERO_DIGITOS);
        }

        return true;
    }
}
