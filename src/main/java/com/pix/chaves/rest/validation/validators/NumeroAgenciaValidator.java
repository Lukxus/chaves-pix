package com.pix.chaves.rest.validation.validators;

import com.pix.chaves.rest.validation.valid.NumeroAgencia;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumeroAgenciaValidator implements ConstraintValidator<NumeroAgencia, String> {
    @Override
    public boolean isValid(String numeroAgencia, ConstraintValidatorContext context) {
        return numeroAgencia != null && numeroAgencia.matches("\\d{1,4}");
    }
}
