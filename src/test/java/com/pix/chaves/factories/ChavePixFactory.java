package com.pix.chaves.factories;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.enums.TipoConta;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.rest.dto.request.ChavePixFilter;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.request.UpdateChavePixRequest;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.rest.dto.response.ChavePixUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChavePixFactory {

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

    public static CreateChavePixRequest createEmailRequestPF() {
        CreateChavePixRequest request = new CreateChavePixRequest();
        request.setTipoChave(TipoChave.EMAIL);
        request.setValorChave(TestConstants.EMAIL);
        request.setTipoConta(TipoConta.CORRENTE);
        request.setNumeroAgencia(TestConstants.AGENCIA);
        request.setNumeroConta(TestConstants.CONTA);
        request.setNomeCorrentista(TestConstants.FIRST_NAME_EMAIL);
        request.setSobrenomeCorrentista(TestConstants.LAST_NAME_EMAIL);
        return request;
    }

    public static CreateChavePixRequest createEmailRequestPJ() {
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

    public static CreateChavePixRequest createAleatorioRequestInvalido() {
        CreateChavePixRequest request = new CreateChavePixRequest();
        request.setTipoChave(null);
        request.setValorChave(null);
        request.setTipoConta(TipoConta.POUPANCA);
        request.setNumeroAgencia(TestConstants.AGENCY_NUMBER_CPF);
        request.setNumeroConta(TestConstants.ACCOUNT_NUMBER_CPF);
        request.setNomeCorrentista(null);
        request.setSobrenomeCorrentista(TestConstants.LAST_NAME_CPF);
        return request;
    }

    public static Page<ChavePixResponse> createPageChavePixEmailResponse() {

        ChavePix chavePix = ChavePix.builder()
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

        ChavePixResponse response1 = ChavePixMapper.toResponse(chavePix);

        ChavePix chavePix1 = ChavePix.builder()
                .id(UUID.randomUUID())
                .tipoChave(TipoChave.EMAIL)
                .valorChave(TestConstants.EMAIL2)
                .tipoConta(TipoConta.POUPANCA)
                .numeroAgencia(TestConstants.AGENCIA2)
                .numeroConta(TestConstants.CONTA2)
                .nomeCorrentista(TestConstants.NOME2)
                .sobrenomeCorrentista(TestConstants.SOBRENOME2)
                .dataHoraInclusao(LocalDateTime.now().plusSeconds(10))
                .dataHoraInativacao(null)
                .build();

        ChavePixResponse response2 = ChavePixMapper.toResponse(chavePix1);

        return new PageImpl<>(List.of(response1, response2), Pageable.unpaged(), 2);
    }

    public static ChavePix createChavePixEmail() {
        return ChavePix.builder()
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
    }

    public static ChavePixResponse createChavePixEmailResponse(ChavePix chavePix) {

        return ChavePixMapper.toResponse(chavePix);
    }

    public static ChavePixUpdateResponse createChavePixEmailUpdateResponse(ChavePix chavePix) {

        return ChavePixMapper.toUpdateResponse(chavePix);
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

    public static ChavePix createChavePixEmailInativada() {
        return ChavePix.builder()
                .id(UUID.randomUUID())
                .tipoChave(TipoChave.EMAIL)
                .valorChave(TestConstants.EMAIL)
                .tipoConta(TipoConta.POUPANCA)
                .numeroAgencia(TestConstants.AGENCIA)
                .numeroConta(TestConstants.CONTA)
                .nomeCorrentista(TestConstants.NOME)
                .sobrenomeCorrentista(TestConstants.SOBRENOME)
                .dataHoraInclusao(LocalDateTime.now())
                .dataHoraInativacao(LocalDateTime.now().plusDays(10))
                .build();
    }

    public static ChavePix createChavePixAleatoria() {
        return ChavePix.builder()
                .id(UUID.randomUUID())
                .tipoChave(TipoChave.ALEATORIA)
                .valorChave(TestConstants.ALEATORIA)
                .tipoConta(TipoConta.POUPANCA)
                .numeroAgencia(TestConstants.AGENCIA)
                .numeroConta(TestConstants.CONTA)
                .nomeCorrentista(TestConstants.NOME)
                .sobrenomeCorrentista(TestConstants.SOBRENOME)
                .dataHoraInclusao(LocalDateTime.now())
                .dataHoraInativacao(null)
                .build();
    }

    public static Page<ChavePix> createPaginaChavePixEmailAleatoria() {
        List<ChavePix> chaves = new ArrayList<>();
        chaves.add(createChavePixEmail());
        chaves.add(createChavePixAleatoria());

        return new PageImpl<>(chaves);
    }

    public static Page<ChavePix> createPaginaComDuasChavesPixEmail() {
        List<ChavePix> chaves = new ArrayList<>();
        chaves.add(createChavePixEmail());
        ChavePix chavePix = createChavePixEmail();
        chavePix.setValorChave(TestConstants.EMAIL2);
        chaves.add(chavePix);

        return new PageImpl<>(chaves);
    }

    public static Page<ChavePix> createPaginaComUmaChavePixEmail() {
        List<ChavePix> chaves = new ArrayList<>();
        chaves.add(createChavePixEmail());
        ChavePix chavePix = createChavePixEmail();
        chavePix.setValorChave(TestConstants.EMAIL2);
        chaves.add(chavePix);

        return new PageImpl<>(chaves);
    }

    public static ChavePixFilter createChavePixFilterComTodosParametrosMenosDataInativacao() {
        return ChavePixFilter.builder()
                .tipoChave(TipoChave.EMAIL)
                .valorChavePix(TestConstants.EMAIL)
                .dataInclusao(LocalDate.now())
                .tipoConta(TipoConta.POUPANCA)
                .numeroAgencia(TestConstants.AGENCIA)
                .numeroConta(TestConstants.CONTA)
                .nomeCorrentista(TestConstants.NOME)
                .nomeCorrentista(TestConstants.SOBRENOME)
                .build();
    }

    public static ChavePixFilter createChavePixFilterComTodosParametrosMenosDataInclusao() {
        return ChavePixFilter.builder()
                .tipoChave(TipoChave.EMAIL)
                .valorChavePix(TestConstants.EMAIL)
                .dataInativacao(LocalDate.now())
                .tipoConta(TipoConta.POUPANCA)
                .numeroAgencia(TestConstants.AGENCIA)
                .numeroConta(TestConstants.CONTA)
                .nomeCorrentista(TestConstants.NOME)
                .nomeCorrentista(TestConstants.SOBRENOME)
                .build();
    }

    public static ChavePixFilter createChavePixFilterComTodosParametros() {
        return ChavePixFilter.builder()
                .tipoChave(TipoChave.EMAIL)
                .valorChavePix(TestConstants.EMAIL)
                .dataInativacao(LocalDate.now())
                .dataInclusao(LocalDate.now())
                .tipoConta(TipoConta.POUPANCA)
                .numeroAgencia(TestConstants.AGENCIA)
                .numeroConta(TestConstants.CONTA)
                .nomeCorrentista(TestConstants.NOME)
                .nomeCorrentista(TestConstants.SOBRENOME)
                .build();
    }

    public static ChavePixFilter createChavePixFilterDataInclusaoETipoContaPoupanca() {
        return ChavePixFilter.builder().dataInclusao(LocalDate.now()).tipoConta(TipoConta.POUPANCA).build();
    }

    public static UpdateChavePixRequest updateChavePixRequest(UUID id) {
        UpdateChavePixRequest request = new UpdateChavePixRequest();
        request.setId(id);
        request.setTipoConta(TipoConta.POUPANCA);
        request.setNumeroAgencia(TestConstants.AGENCIA2);
        request.setNumeroConta(TestConstants.CONTA2);
        request.setNomeCorrentista(TestConstants.NOME2);
        request.setSobrenomeCorrentista(TestConstants.SOBRENOME2);
        return request;
    }


}
