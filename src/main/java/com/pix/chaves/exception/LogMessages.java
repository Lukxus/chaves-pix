package com.pix.chaves.exception;

public class LogMessages {

    public static final String INICIANDO_CRIACAO_CHAVE_PIX = "Iniciando criação da chave PIX: {}";
    public static final String CHAVE_PIX_JA_CADASTRADA = "Tentativa de criação de chave PIX já cadastrada: {}";
    public static final String CHAVE_PIX_CRIADA_COM_SUCESSO = "Chave PIX criada com sucesso: ID {}";

    public static final String INICIANDO_ATUALIZACAO_CHAVE_PIX = "Iniciando atualização da chave PIX: ID {}";
    public static final String CHAVE_PIX_ALTERADA_COM_SUCESSO = "Chave PIX atualizada com sucesso: ID {}";
    public static final String CHAVE_PIX_NAO_ENCONTRADA = "Chave PIX não encontrada: ID {}";
    public static final String CHAVE_PIX_INATIVADA = "Tentativa de alteração de chave PIX inativada: ID {}";

    public static final String INICIANDO_INATIVACAO_CHAVE_PIX = "Iniciando inativação da chave PIX: ID {}";
    public static final String CHAVE_PIX_NAO_ENCONTRADA_INATIVACAO = "Tentativa de inativação de chave PIX não encontrada: ID {}";
    public static final String CHAVE_PIX_JA_INATIVADA = "Chave PIX já inativada: ID {}";
    public static final String CHAVE_PIX_INATIVADA_COM_SUCESSO = "Chave PIX inativada com sucesso: ID {}";

    public static final String CONSULTANDO_CHAVE_PIX = "Consultando chave PIX: ID {}";
    public static final String CHAVE_PIX_ENCONTRADA = "Chave PIX encontrada: ID {}";

    public static final String CONSULTANDO_CHAVES_POR_AGENCIA_CONTA = "Consultando chaves PIX para agência {} e conta {}";
    public static final String CONSULTA_CONCLUIDA_TOTAL_CHAVES = "Consulta concluída. Total de chaves encontradas: {}";

    public static final String CONSULTANDO_CHAVES_POR_TIPO = "Consultando chaves PIX do tipo: {}";
    public static final String CONSULTANDO_CHAVES_POR_NOME = "Iniciando consulta de chaves PIX para: {} {}";
    public static final String CONSULTANDO_CHAVES_POR_PERIODO_INCLUSAO = "Consultando chaves PIX por período de inclusão: {} até {}";
    public static final String CONSULTANDO_CHAVES_POR_PERIODO_EXCLUSAO = "Consultando chaves PIX por período de exclusão: {} até {}";

    public static final String ERRO_CONSULTA_CHAVES_POR_NOME = "Erro ao consultar chaves PIX para: {} {}. Erro: {}";
}
