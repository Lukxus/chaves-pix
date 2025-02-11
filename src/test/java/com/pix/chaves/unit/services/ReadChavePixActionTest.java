package com.pix.chaves.unit.services;

import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.FiltroInvalidoException;
import com.pix.chaves.factories.ChavePixFactory;
import com.pix.chaves.factories.TestConstants;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.request.ChavePixFilter;
import com.pix.chaves.rest.dto.request.ChavePixFilterSpecification;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.services.chavesPix.ReadChavePixAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadChavePixActionTest {

    @Mock
    private ChavePixRepository repository;

    @InjectMocks
    private ReadChavePixAction readChavePixAction;

    @Test
    void deveListarChavePixPeloId() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        UUID id = chavePix.getId();

        when(repository.findByIdAndDataHoraInativacaoIsNull(id)).thenReturn(Optional.of(chavePix));

        ChavePix chaveEncontrada = readChavePixAction.findById(id);

        assertEquals(chavePix.getId(), chaveEncontrada.getId());
        assertEquals(chavePix.getValorChave(), chaveEncontrada.getValorChave());
        assertEquals(chavePix.getTipoChave(), chaveEncontrada.getTipoChave());
        assertEquals(chavePix.getNomeCorrentista(), chaveEncontrada.getNomeCorrentista());
        assertEquals(chavePix.getSobrenomeCorrentista(), chaveEncontrada.getSobrenomeCorrentista());
        assertEquals(chavePix.getNumeroConta(), chaveEncontrada.getNumeroConta());
        assertEquals(chavePix.getNumeroAgencia(), chaveEncontrada.getNumeroAgencia());
        assertEquals(chavePix.getTipoConta(), chaveEncontrada.getTipoConta());
        verify(repository, times(1)).findByIdAndDataHoraInativacaoIsNull(id);
    }

    @Test
    void deveListarChavePixUsandoTodosOsCamposDoFilterMenosDataInativacao() {
        Page<ChavePix> chavePixPaginada = ChavePixFactory.createPaginaComUmaChavePixEmail();

        when(repository.findAll(any(ChavePixFilterSpecification.class), any(Pageable.class)))
                .thenReturn(chavePixPaginada);

        ChavePixFilter chavePixFilter = ChavePixFactory.createChavePixFilterComTodosParametrosMenosDataInativacao();

        Pageable pageable = PageRequest.of(0, 10);
        Page<ChavePixResponse> respostaPage = readChavePixAction.getChavesPaginada(chavePixFilter, pageable);

        ChavePix chaveEsperada = chavePixPaginada.getContent().get(0);
        ChavePixResponse respostaEncontrada = respostaPage.getContent().get(0);

        assertEquals(chaveEsperada.getId(), respostaEncontrada.getId());
        assertEquals(chaveEsperada.getValorChave(), respostaEncontrada.getValorChave());
        assertEquals(chaveEsperada.getTipoChave(), respostaEncontrada.getTipoChave());
        assertEquals(chaveEsperada.getNomeCorrentista(), respostaEncontrada.getNomeCorrentista());
        assertEquals(chaveEsperada.getSobrenomeCorrentista(), respostaEncontrada.getSobrenomeCorrentista());
        assertEquals(chaveEsperada.getNumeroConta(), respostaEncontrada.getNumeroConta());
        assertEquals(chaveEsperada.getNumeroAgencia(), respostaEncontrada.getNumeroAgencia());
        assertEquals(chaveEsperada.getTipoConta(), respostaEncontrada.getTipoConta());

        verify(repository, times(1)).findAll(any(ChavePixFilterSpecification.class), any(Pageable.class));
    }

    @Test
    void deveListarChavePixUsandoTodosOsCamposDoFilterMenosDataInclusao() {
        Page<ChavePix> chavePixPaginada = ChavePixFactory.createPaginaComUmaChavePixEmail();

        when(repository.findAll(any(ChavePixFilterSpecification.class), any(Pageable.class)))
                .thenReturn(chavePixPaginada);

        ChavePixFilter chavePixFilter = ChavePixFactory.createChavePixFilterComTodosParametrosMenosDataInclusao();

        Pageable pageable = PageRequest.of(0, 10);
        Page<ChavePixResponse> respostaPage = readChavePixAction.getChavesPaginada(chavePixFilter, pageable);

        ChavePix chaveEsperada = chavePixPaginada.getContent().get(0);
        ChavePixResponse respostaEncontrada = respostaPage.getContent().get(0);

        assertEquals(chaveEsperada.getId(), respostaEncontrada.getId());
        assertEquals(chaveEsperada.getValorChave(), respostaEncontrada.getValorChave());
        assertEquals(chaveEsperada.getTipoChave(), respostaEncontrada.getTipoChave());
        assertEquals(chaveEsperada.getNomeCorrentista(), respostaEncontrada.getNomeCorrentista());
        assertEquals(chaveEsperada.getSobrenomeCorrentista(), respostaEncontrada.getSobrenomeCorrentista());
        assertEquals(chaveEsperada.getNumeroConta(), respostaEncontrada.getNumeroConta());
        assertEquals(chaveEsperada.getNumeroAgencia(), respostaEncontrada.getNumeroAgencia());
        assertEquals(chaveEsperada.getTipoConta(), respostaEncontrada.getTipoConta());

        verify(repository, times(1)).findAll(any(ChavePixFilterSpecification.class), any(Pageable.class));
    }

    @Test
    void deveListarChavePixUsandoTodosOsCamposDoFilter() {
        ChavePixFilter chavePixFilter = ChavePixFactory.createChavePixFilterComTodosParametros();

        Pageable pageable = PageRequest.of(0, 10);
        FiltroInvalidoException exception = assertThrows(FiltroInvalidoException.class,
                () -> readChavePixAction.getChavesPaginada(chavePixFilter, pageable)
        );
        assertEquals(ErrorMessages.FILTRO_DATA_INCLUSAO_E_DATA_INATIVACAO, exception.getMessage());

    }

    @Test
    void deveListarChavePixPeloFilterDataInclusaoHojeETipoContaPoupanca() {
        Page<ChavePix> chavePixPaginada = ChavePixFactory.createPaginaChavePixEmailAleatoria();

        when(repository.findAll(any(ChavePixFilterSpecification.class), any(Pageable.class)))
                .thenReturn(chavePixPaginada);

        ChavePixFilter chavePixFilter = ChavePixFactory.createChavePixFilterDataInclusaoETipoContaPoupanca();

        Pageable pageable = PageRequest.of(0, 10);
        Page<ChavePixResponse> respostaPage = readChavePixAction.getChavesPaginada(chavePixFilter, pageable);

        ChavePix chaveEsperada = chavePixPaginada.getContent().get(0);
        ChavePixResponse respostaEncontrada = respostaPage.getContent().get(0);

        assertEquals(chaveEsperada.getId(), respostaEncontrada.getId());
        assertEquals(chaveEsperada.getValorChave(), respostaEncontrada.getValorChave());
        assertEquals(chaveEsperada.getTipoChave(), respostaEncontrada.getTipoChave());
        assertEquals(chaveEsperada.getNomeCorrentista(), respostaEncontrada.getNomeCorrentista());
        assertEquals(chaveEsperada.getSobrenomeCorrentista(), respostaEncontrada.getSobrenomeCorrentista());
        assertEquals(chaveEsperada.getNumeroConta(), respostaEncontrada.getNumeroConta());
        assertEquals(chaveEsperada.getNumeroAgencia(), respostaEncontrada.getNumeroAgencia());
        assertEquals(chaveEsperada.getTipoConta(), respostaEncontrada.getTipoConta());

        ChavePix chaveEsperada1 = chavePixPaginada.getContent().get(1);
        ChavePixResponse respostaEncontrada1 = respostaPage.getContent().get(1);

        assertEquals(chaveEsperada1.getId(), respostaEncontrada1.getId());
        assertEquals(chaveEsperada1.getValorChave(), respostaEncontrada1.getValorChave());
        assertEquals(chaveEsperada1.getTipoChave(), respostaEncontrada1.getTipoChave());
        assertEquals(chaveEsperada1.getNomeCorrentista(), respostaEncontrada1.getNomeCorrentista());
        assertEquals(chaveEsperada1.getSobrenomeCorrentista(), respostaEncontrada1.getSobrenomeCorrentista());
        assertEquals(chaveEsperada1.getNumeroConta(), respostaEncontrada1.getNumeroConta());
        assertEquals(chaveEsperada1.getNumeroAgencia(), respostaEncontrada1.getNumeroAgencia());
        assertEquals(chaveEsperada1.getTipoConta(), respostaEncontrada1.getTipoConta());
        verify(repository, times(1)).findAll(any(ChavePixFilterSpecification.class), any(Pageable.class));
    }

    @Test
    void deveContarAQuantidadeDeChavePixPelaAgenciaEConta() {
        long quatidadeChaves = 1l;
        when(repository.countByNumeroAgenciaAndNumeroContaAndDataHoraInativacaoIsNull(anyString(), anyString()))
                .thenReturn(quatidadeChaves);

        long quantidadeChavesEncontrada = readChavePixAction.countByAgenciaConta(TestConstants.AGENCIA, TestConstants.CONTA);

        assertEquals(quatidadeChaves, quantidadeChavesEncontrada);
        verify(repository, times(1)).countByNumeroAgenciaAndNumeroContaAndDataHoraInativacaoIsNull(anyString(), anyString());
    }
}
