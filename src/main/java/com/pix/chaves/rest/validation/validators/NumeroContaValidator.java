package com.pix.chaves.rest.validation.validators;

import com.pix.chaves.rest.validation.valid.NumeroConta;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumeroContaValidator implements ConstraintValidator<NumeroConta, String> {
    @Override
    public boolean isValid(String numeroConta, ConstraintValidatorContext context) {
        return numeroConta != null && numeroConta.matches("\\d{1,8}");
    }
}
