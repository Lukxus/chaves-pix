package com.pix.chaves.utils.validation.valid;

import com.pix.chaves.exception.ChavePixValidationException;

public interface ChavePixValidator {
    void validate(String valor) throws ChavePixValidationException;
}
