package com.pix.chaves.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum TipoChave {
    CELULAR(1, "Número de Celular"),
    EMAIL(2, "Endereço de E-mail"),
    CPF(3, "Cadastro de Pessoa Física"),
    CNPJ(4, "Cadastro Nacional de Pessoa Jurídica"),
    ALEATORIA(5, "Chave Aleatória");

    private final int id;
    private final String descricao;

    TipoChave(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}