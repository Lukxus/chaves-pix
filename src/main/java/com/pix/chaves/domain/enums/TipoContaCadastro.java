package com.pix.chaves.domain.enums;

public enum TipoContaCadastro {
    FISICA(1, "Pessoa Fisica"),
    JURIDICA(2, "Pessoa Jurifica");

    private final int id;
    private final String descricao;

    TipoContaCadastro(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
