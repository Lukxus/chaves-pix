package com.pix.chaves.domain.enums;

import lombok.Getter;

@Getter
public enum TipoConta {

    CORRENTE(1, "Conta Corrente"),
    POUPANCA(2, "Conta Poupança");

    private final int id;
    private final String descricao;

    TipoConta(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}