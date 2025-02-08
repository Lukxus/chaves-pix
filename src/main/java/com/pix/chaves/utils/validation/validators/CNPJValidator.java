package com.pix.chaves.utils.validation.validators;

import com.pix.chaves.exception.ChavePixValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.utils.validation.valid.ChavePixValidator;

public class CNPJValidator implements ChavePixValidator {

    @Override
    public void validate(String valor) throws ChavePixValidationException {

        if (valor == null || valor.trim().isEmpty()) {
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CNPJ_VAZIO_NULO);
        }

        if (!valor.matches("\\d+")) {
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CNPJ_DIGITOS_NUMERICOS);
        }

        if (valor.length() > 14) {
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CNPJ_NUMERO_MAXIMO_CARACTERES);
        }

        if (valor.length() < 14) {
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CNPJ_NUMERO_MINIMO_CARACTERES);
        }

        if (!isValidCNPJ(valor)) {
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CNPJ_FORMATO_INVALIDO);
        }
    }

    private boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        char[] cnpjArray = cnpj.toCharArray();
        int soma = 0;
        int peso[] = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        for (int i = 0; i < 12; i++) {
            int num = (cnpjArray[i] - '0');
            soma += num * peso[i];
        }

        int resto = soma % 11;
        int digitoVerificador1 = (resto < 2) ? 0 : 11 - resto;

        if (digitoVerificador1 != (cnpjArray[12] - '0')) {
            return false;
        }

        soma = 0;
        int peso2[] = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        for (int i = 0; i < 13; i++) {
            int num = (cnpjArray[i] - '0');
            soma += num * peso2[i];
        }

        resto = soma % 11;
        int digitoVerificador2 = (resto < 2) ? 0 : 11 - resto;

        if (digitoVerificador2 != (cnpjArray[13] - '0')) {
            return false;
        }

        return true;
    }
}
