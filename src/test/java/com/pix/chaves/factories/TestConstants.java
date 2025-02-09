package com.pix.chaves.factories;

public final class TestConstants {


    private TestConstants() {
        throw new UnsupportedOperationException("Esta é uma classe de utilitários e não pode ser instanciada");
    }


    public static final String AGENCIA = "0001";
    public static final String AGENCIA2 = "0002";
    public static final String CONTA = "123456";
    public static final String CONTA2 = "234567";
    public static final String NOME = "BELTRANO";
    public static final String NOME2 = "SICRANO";
    public static final String SOBRENOME = "Silva";
    public static final String SOBRENOME2 = "Oliveira";


    // Constantes para a requisição de email
    public static final String EMAIL = "email@dominio.com";
    public static final String EMAIL2 = "email2@dominio.com";
    public static final String EMAIL_SEM_AT = "emaildominio.com";
    public static final String EMAIL_COM_TAMANHO_MAIOR_QUE_O_LIMITE = "emailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemail@dominio.com";
    public static final String AGENCY_NUMBER_EMAIL = "0001";
    public static final String ACCOUNT_NUMBER_EMAIL = "123456";
    public static final String FIRST_NAME_EMAIL = "Fulano";
    public static final String LAST_NAME_EMAIL = "da Silva";

    // Constantes para a requisição de CPF
    public static final String CPF = "23025142030";
    public static final String CPF_ALFANUMERICO = "1234BC78901";
    public static final String CPF_COM_TAMANHO_MAIOR_QUE_O_LIMITE = "1234BC789011111";
    public static final String CPF_COM_TAMANHO_MENOR_QUE_O_LIMITE = "1234BC7";
    public static final String CPF_VALOR_INVALIDO = "11111111111";
    public static final String AGENCY_NUMBER_CPF = "0002";
    public static final String ACCOUNT_NUMBER_CPF = "654321";
    public static final String FIRST_NAME_CPF = "Beltrano";
    public static final String LAST_NAME_CPF = "de Tal";

    // Constantes para a requisição de Celular
    public static final String CELULAR = "+5511982258884";
    public static final String CELULAR_SEM_MAIS_NO_COMECO = "5511982258884";
    public static final String CELULAR_COM_TAMANHO_MAIOR_QUE_O_LIMITE = "55111111111111111111111982258884";
    public static final String CELULAR_COM_ALFANUMERICO = "a5511982258884";
//    public static final String AGENCY_NUMBER_CPF = "0002";
//    public static final String ACCOUNT_NUMBER_CPF = "654321";
//    public static final String FIRST_NAME_CPF = "Beltrano";
//    public static final String LAST_NAME_CPF = "de Tal";

    // Constantes para a requisição de CNPJ
    public static final String CNPJ = "16010624000120";
    public static final String CNPJ_ALFANUMERICO = "1601062ab00120";
    public static final String CNPJ_COM_TAMANHO_MAIOR_QUE_O_LIMITE = "160106240001201111";
    public static final String CNPJ_COM_TAMANHO_MENOR_QUE_O_LIMITE = "160106240001";
    public static final String CNPJ_INVALIDO = "11111111111111";

    // Constantes para a requisição de ALEATORIA
    public static final String ALEATORIA = "lH1oRWu0BekZ32L3szY8hC";
    public static final String ALEATORIA_SIMBOLOS = "lH1oRWu0BekZ32L3szY8----__hC";
    public static final String ALEATORIA_MAIOR_QUE_O_LIMITE = "lH1oRWu0BekZ32L3szY811111111111111111111111111111111111111111111111111111111111hC";


}
