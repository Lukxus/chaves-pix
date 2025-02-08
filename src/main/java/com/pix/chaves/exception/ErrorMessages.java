package com.pix.chaves.exception;

public class ErrorMessages {
    public static final String CHAVE_PIX_JA_CADASTRADA = "Chave PIX já cadastrada.";
    public static final String CHAVE_PIX_NAO_ENCONTRADA = "Chave PIX não encontrada.";
    public static final String CHAVE_PIX_JA_INATIVADA = "Chave PIX já inativada.";
    public static final String CHAVE_PIX_INATIVADA_NAO_PODE_SER_ALTERADA = "Não é possível alterar uma chave inativada.";
    public static final String ERRO_CONSULTA_CHAVES = "Erro ao consultar chaves PIX. Erro: {}";
    public static final String TIPO_CHAVE_NAO_SUPORTADO = "Tipo de chave não suportado: ";
    public static final String CHAVE_ALEATORIA_ALFANUMERICOS = "Chave Aleatória deve conter apenas caracteres alfanuméricos.";
    public static final String CHAVE_ALEATORIA_VAZIA_NULA = "Chave Aleatória não pode ser vazia ou nula.";
    public static final String CHAVE_ALEATORIA_CARACTERES_MAXIMOS = "Chave Aleatória deve conter no máximo 36 caracteres.";
    public static final String CHAVE_CELULAR_CARACTERES_MAIS = "Celular deve iniciar com o símbolo '+'.";
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
    public static final String CHAVE_EMAIL_NUMERO_MAXIMO_CARACTERES = "Email deve ter no máximo 77 caracteres";
    public static final String NUMERO_AGENCIA_VAZIO_NULO = "O número da agência não pode ser nulo ou vazio.";
    public static final String NUMERO_AGENCIA_NUMERO_DIGITOS = "O número da agência deve ter de 1 a 4 dígitos numéricos.";
    public static final String NUMERO_CONTA_VAZIO_NULO = "O número da conta não pode ser nulo ou vazio.";
    public static final String NUMERO_CONTA_NUMERO_DIGITOS = "O número da conta deve ter de 1 a 8 dígitos numéricos.";
}