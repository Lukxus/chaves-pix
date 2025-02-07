package com.pix.chaves.rest.validation.validators;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.rest.dto.ChavePixRequest;
import com.pix.chaves.rest.validation.valid.ValidarChavePix;
import com.pix.chaves.services.ChavePixService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ChavePixValidator implements ConstraintValidator<ValidarChavePix, ChavePixRequest> {

    private final ChavePixService chavePixService;

    public ChavePixValidator(ChavePixService chavePixService) {
        this.chavePixService = chavePixService;
    }

    @Override
    public boolean isValid(ChavePixRequest request, ConstraintValidatorContext context) {
        if (request.getTipoChave() == null || request.getValorChave() == null) {
            return false;
        }

        String valorChave = request.getValorChave();
        TipoChave tipoChave = request.getTipoChave();

//        if (chavePixService.chaveJaExiste(valorChave)) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("O valor da chave já está cadastrado.")
//                    .addConstraintViolation();
//            return false;
//        }

        return switch (tipoChave) {
            case CPF -> validarCPF(valorChave);
            case CNPJ -> validarCNPJ(valorChave);
            case EMAIL -> validarEmail(valorChave);
            case CELULAR -> validarTelefone(valorChave);
            case ALEATORIA -> validarChaveAleatoria(valorChave);
        };
    }

    private boolean validarCPF(String cpf) {
        return cpf.matches("\\d{11}") && validarCPFNumerico(cpf);
    }

    private boolean validarCNPJ(String cnpj) {
        return cnpj.matches("\\d{14}") && validarCNPJNumerico(cnpj);
    }

    private boolean validarEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") && email.length() <= 77;
    }

    private boolean validarTelefone(String telefone) {
        return telefone.matches("^\\+\\d{1,2}\\d{2}\\d{9}$");
    }

    private boolean validarChaveAleatoria(String chave) {
        return chave.matches("^[A-Za-z0-9]{1,36}$");
    }

    private boolean validarCPFNumerico(String cpf) {
        // Algoritmo de validação de CPF
        return true; // Implementar lógica aqui
    }

    private boolean validarCNPJNumerico(String cnpj) {
        // Algoritmo de validação de CNPJ
        return true; // Implementar lógica aqui
    }
}
