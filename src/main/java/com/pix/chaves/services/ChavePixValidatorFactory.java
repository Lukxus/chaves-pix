package com.pix.chaves.services;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.utils.validation.valid.ChavePixValidator;
import com.pix.chaves.utils.validation.validators.CNPJValidator;
import com.pix.chaves.utils.validation.validators.CPFValidator;
import com.pix.chaves.utils.validation.validators.CelularValidator;
import com.pix.chaves.utils.validation.validators.ChaveAleatoriaValidator;
import com.pix.chaves.utils.validation.validators.EmailValidator;

public class ChavePixValidatorFactory {
    public static ChavePixValidator getValidator(TipoChave tipoChave) {
        switch (tipoChave) {
            case TipoChave.CELULAR:
                return new CelularValidator();
            case TipoChave.EMAIL:
                return new EmailValidator();
            case TipoChave.CPF:
                return new CPFValidator();
            case TipoChave.CNPJ:
                return new CNPJValidator();
            case TipoChave.ALEATORIA:
                return new ChaveAleatoriaValidator();
            default:
                throw new IllegalArgumentException(ErrorMessages.TIPO_CHAVE_NAO_SUPORTADO + tipoChave);
        }
    }
}
