package com.pix.chaves.factories;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.enums.TipoConta;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.response.ChavePixResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ChavePixRequestFactory {

    public static CreateChavePixRequest createEmailRequest() {
        CreateChavePixRequest request = new CreateChavePixRequest();
        request.setTipoChave(TipoChave.EMAIL);
        request.setValorChave(TestConstants.EMAIL);
        request.setTipoConta(TipoConta.CORRENTE);
        request.setNumeroAgencia(TestConstants.AGENCY_NUMBER_EMAIL);
        request.setNumeroConta(TestConstants.ACCOUNT_NUMBER_EMAIL);
        request.setNomeCorrentista(TestConstants.FIRST_NAME_EMAIL);
        request.setSobrenomeCorrentista(TestConstants.LAST_NAME_EMAIL);
        return request;
    }

    public static CreateChavePixRequest createCelularRequest() {
        CreateChavePixRequest request = new CreateChavePixRequest();
        request.setTipoChave(TipoChave.CELULAR);
        request.setValorChave(TestConstants.CELULAR);
        request.setTipoConta(TipoConta.CORRENTE);
        request.setNumeroAgencia(TestConstants.AGENCY_NUMBER_EMAIL);
        request.setNumeroConta(TestConstants.ACCOUNT_NUMBER_EMAIL);
        request.setNomeCorrentista(TestConstants.FIRST_NAME_EMAIL);
        request.setSobrenomeCorrentista(TestConstants.LAST_NAME_EMAIL);
        return request;
    }

    public static CreateChavePixRequest createCpfRequest() {
        CreateChavePixRequest request = new CreateChavePixRequest();
        request.setTipoChave(TipoChave.CPF);
        request.setValorChave(TestConstants.CPF);
        request.setTipoConta(TipoConta.POUPANCA);
        request.setNumeroAgencia(TestConstants.AGENCY_NUMBER_CPF);
        request.setNumeroConta(TestConstants.ACCOUNT_NUMBER_CPF);
        request.setNomeCorrentista(TestConstants.FIRST_NAME_CPF);
        request.setSobrenomeCorrentista(TestConstants.LAST_NAME_CPF);
        return request;
    }

    public static CreateChavePixRequest createCNPJRequest() {
        CreateChavePixRequest request = new CreateChavePixRequest();
        request.setTipoChave(TipoChave.CNPJ);
        request.setValorChave(TestConstants.CNPJ);
        request.setTipoConta(TipoConta.POUPANCA);
        request.setNumeroAgencia(TestConstants.AGENCY_NUMBER_CPF);
        request.setNumeroConta(TestConstants.ACCOUNT_NUMBER_CPF);
        request.setNomeCorrentista(TestConstants.FIRST_NAME_CPF);
        request.setSobrenomeCorrentista(TestConstants.LAST_NAME_CPF);
        return request;
    }

    public static CreateChavePixRequest createAleatorioRequest() {
        CreateChavePixRequest request = new CreateChavePixRequest();
        request.setTipoChave(TipoChave.ALEATORIA);
        request.setValorChave(TestConstants.ALEATORIA);
        request.setTipoConta(TipoConta.POUPANCA);
        request.setNumeroAgencia(TestConstants.AGENCY_NUMBER_CPF);
        request.setNumeroConta(TestConstants.ACCOUNT_NUMBER_CPF);
        request.setNomeCorrentista(TestConstants.FIRST_NAME_CPF);
        request.setSobrenomeCorrentista(TestConstants.LAST_NAME_CPF);
        return request;
    }

    public static List<ChavePixResponse> createListChavePixEmailResponse() {
        ChavePixResponse response1 = ChavePixResponse.builder()
                .id(UUID.randomUUID())
                .tipoChave(TipoChave.EMAIL)
                .valorChave(TestConstants.EMAIL)
                .tipoConta(TipoConta.POUPANCA)
                .numeroAgencia(TestConstants.AGENCIA)
                .numeroConta(TestConstants.CONTA)
                .nomeCorrentista(TestConstants.NOME)
                .sobrenomeCorrentista(TestConstants.SOBRENOME)
                .dataHoraInclusao(LocalDateTime.now())
                .dataHoraExclusao(null)
                .build();

        ChavePixResponse response2 = ChavePixResponse.builder()
                .id(UUID.randomUUID())
                .tipoChave(TipoChave.EMAIL)
                .valorChave(TestConstants.EMAIL2)
                .tipoConta(TipoConta.POUPANCA)
                .numeroAgencia(TestConstants.AGENCIA2)
                .numeroConta(TestConstants.AGENCIA2)
                .nomeCorrentista(TestConstants.NOME2)
                .sobrenomeCorrentista(TestConstants.SOBRENOME2)
                .dataHoraInclusao(LocalDateTime.now().plusSeconds(10))
                .dataHoraExclusao(null)
                .build();

        return List.of(response1, response2);
    }

    public static List<ChavePix> createListChavePixEmail() {
        ChavePix response1 = ChavePix.builder()
                .id(UUID.randomUUID())
                .tipoChave(TipoChave.EMAIL)
                .valorChave(TestConstants.EMAIL)
                .tipoConta(TipoConta.POUPANCA)
                .numeroAgencia(TestConstants.AGENCIA)
                .numeroConta(TestConstants.CONTA)
                .nomeCorrentista(TestConstants.NOME)
                .sobrenomeCorrentista(TestConstants.SOBRENOME)
                .dataHoraInclusao(LocalDateTime.now())
                .dataHoraInativacao(null)
                .build();

        ChavePix response2 = ChavePix.builder()
                .id(UUID.randomUUID())
                .tipoChave(TipoChave.EMAIL)
                .valorChave(TestConstants.EMAIL2)
                .tipoConta(TipoConta.POUPANCA)
                .numeroAgencia(TestConstants.AGENCIA2)
                .numeroConta(TestConstants.AGENCIA2)
                .nomeCorrentista(TestConstants.NOME2)
                .sobrenomeCorrentista(TestConstants.SOBRENOME2)
                .dataHoraInclusao(LocalDateTime.now().plusSeconds(10))
                .dataHoraInativacao(null)
                .build();

        return List.of(response1, response2);
    }

}
