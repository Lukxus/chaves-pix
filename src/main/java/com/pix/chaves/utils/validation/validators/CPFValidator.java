package com.pix.chaves.utils.validation.validators;

import com.pix.chaves.exception.ChavePixValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.LogMessages;
import com.pix.chaves.utils.validation.valid.ChavePixValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CPFValidator implements ChavePixValidator {

    @Override
    public void validate(String valor) throws ChavePixValidationException {

        if (valor == null || valor.trim().isEmpty()) {
            log.error(LogMessages.CHAVE_CPF_VAZIO_NULO);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CPF_VAZIO_NULO);
        }

        if (!valor.matches("\\d+")) {
            log.error(LogMessages.CHAVE_CPF_DIGITOS_NUMERICOS);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CPF_DIGITOS_NUMERICOS);
        }

        if (valor.length() > 11) {
            log.error(LogMessages.CHAVE_CPF_NUMERO_MAXIMO_CARACTERES);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CPF_NUMERO_MAXIMO_CARACTERES);
        }

        if (valor.length() < 11) {
            log.error(LogMessages.CHAVE_CPF_NUMERO_MINIMO_CARACTERES);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CPF_NUMERO_MINIMO_CARACTERES);
        }

        if (!isValidCPF(valor)) {
            log.error(LogMessages.CHAVE_CPF_FORMATO_INVALIDO);
            throw new ChavePixValidationException(ErrorMessages.CHAVE_CPF_FORMATO_INVALIDO);
        }
    }

    private boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }
        if (resto != (cpf.charAt(9) - '0')) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }
        if (resto != (cpf.charAt(10) - '0')) {
            return false;
        }

        return true;
    }
}
