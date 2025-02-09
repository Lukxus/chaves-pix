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
    public static final String CONTANDO_CHAVES_POR_AGENCIA_CONTA = "Contando chaves pela agência {} e conta {}";
    public static final String TOTAL_CHAVES_POR_AGENCIA_CONTA ="Total de chaves encontradas: {}";
    public static final String CONSULTA_CONCLUIDA_TOTAL_CHAVES = "Consulta concluída. Total de chaves encontradas: {}";

    public static final String CONSULTANDO_CHAVES_POR_TIPO = "Consultando chaves PIX do tipo: {}";
    public static final String CONSULTANDO_CHAVES_POR_NOME = "Iniciando consulta de chaves PIX para: {} {}";
    public static final String CONSULTANDO_CHAVES_POR_PERIODO_INCLUSAO = "Consultando chaves PIX por período de inclusão: {} até {}";
    public static final String CONSULTANDO_CHAVES_POR_PERIODO_EXCLUSAO = "Consultando chaves PIX por período de exclusão: {} até {}";

    public static final String ERRO_CONSULTA_CHAVES_POR_NOME = "Erro ao consultar chaves PIX para: {} {}. Erro: {}";

    public static final String CHAVE_ALEATORIA_ALFANUMERICOS = "Chave Aleatória deve conter apenas caracteres alfanuméricos.";
    public static final String CHAVE_ALEATORIA_VAZIA_NULA = "Chave Aleatória não pode ser vazia ou nula.";
    public static final String CHAVE_ALEATORIA_CARACTERES_MAXIMOS = "Chave Aleatória deve conter no máximo 36 caracteres.";
    public static final String CHAVE_CELULAR_CARACTERES_MAIS = "Celular deve iniciar com o símbolo '+'.";
    public static final String CHAVE_CELULAR_NULA = "Celular não pode ser vazia";
    public static final String CHAVE_CELULAR_FORMATO_INVALIDO = "Formato inválido para número de celular.";
    public static final String CHAVE_CNPJ_VAZIO_NULO = "CNPJ não pode ser vazio ou nulo.";
    public static final String CHAVE_CNPJ_DIGITOS_NUMERICOS = "CNPJ deve conter somente dígitos numéricos.";
    public static final String CHAVE_CNPJ_NUMERO_MAXIMO_CARACTERES = "CNPJ deve conter no máximo 14 dígitos.";
    public static final String CHAVE_CNPJ_NUMERO_MINIMO_CARACTERES = "CNPJ deve conter 14 dígitos para ser válido.";
    public static final String CHAVE_CNPJ_FORMATO_INVALIDO = "CNPJ inválido.";
    public static final String CHAVE_CPF_VAZIO_NULO = "CPF não pode ser vazio ou nulo.";
    public static final String CHAVE_CPF_DIGITOS_NUMERICOS = "CPF deve conter somente dígitos numéricos.";
    public static final String CHAVE_CPF_NUMERO_MAXIMO_CARACTERES = "CPF deve conter no máximo 11 dígitos.";
    public static final String CHAVE_CPF_NUMERO_MINIMO_CARACTERES = "CPF deve conter 11 dígitos para ser válido.";
    public static final String CHAVE_CPF_FORMATO_INVALIDO = "CPF inválido.";
    public static final String CHAVE_EMAIL_CARACTER_AT = "Email deve conter o símbolo '@'";
    public static final String CHAVE_EMAIL_NULO = "Email nao pode ser nulo";
    public static final String CHAVE_EMAIL_NUMERO_MAXIMO_CARACTERES = "Email deve ter no máximo 77 caracteres";
    public static final String NUMERO_AGENCIA_VAZIO_NULO = "O número da agência não pode ser nulo ou vazio.";
    public static final String NUMERO_AGENCIA_NUMERO_DIGITOS = "O número da agência deve ter de 1 a 4 dígitos numéricos.";
    public static final String NUMERO_CONTA_VAZIO_NULO = "O número da conta não pode ser nulo ou vazio.";
    public static final String NUMERO_CONTA_NUMERO_DIGITOS = "O número da conta deve ter de 1 a 8 dígitos numéricos.";

    public static final String CONTA_JA_CADASTRADA = "Conta e Agencia ja cadastrados. Agencia: {} ; Conta: {}";
    public static final String CONTA_NAO_CADASTRADA = "Conta não cadastrada. Agencia: {} ; Conta: {}.";
}
