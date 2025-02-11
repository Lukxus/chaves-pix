package com.pix.chaves.unit.services;

import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.FiltroInvalidoException;
import com.pix.chaves.exception.RecursoNaoEncontradoException;
import com.pix.chaves.exception.ValidationException;
import com.pix.chaves.factories.ChavePixFactory;
import com.pix.chaves.rest.dto.request.ChavePixFilter;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.request.UpdateChavePixRequest;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.rest.dto.response.ChavePixUpdateResponse;
import com.pix.chaves.services.chavesPix.ChavePixService;
import com.pix.chaves.services.chavesPix.CreateChavePixAction;
import com.pix.chaves.services.chavesPix.ReadChavePixAction;
import com.pix.chaves.services.chavesPix.RemoveChavePixAction;
import com.pix.chaves.services.chavesPix.UpdateChavePixAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChavePixServiceTest {

    @InjectMocks
    private ChavePixService chavePixService;

    @Mock
    private CreateChavePixAction createChavePixAction;

    @Mock
    private UpdateChavePixAction updateChavePixAction;

    @Mock
    private ReadChavePixAction readChavePixAction;

    @Mock
    RemoveChavePixAction removeChavePixAction;

    @Test
    void deveCriarChavePixComDadosValidos() {
        ChavePix chavePix = ChavePixFactory.createChavePixAleatoria();
        CreateChavePixRequest request = ChavePixFactory.createAleatorioRequest();

        when(createChavePixAction.createChavePix(request)).thenReturn(chavePix);

        UUID chaveId = chavePixService.criarChavePix(request);

        assertNotNull(chaveId);
        assertEquals(chavePix.getId(), chaveId);
        verify(createChavePixAction, times(1)).createChavePix(request);
    }

    @Test
    void deveLancarExcecaoAoTentarCriarChavePixComDadosInvalidos() {
        CreateChavePixRequest request = ChavePixFactory.createAleatorioRequestInvalido();

        when(createChavePixAction.createChavePix(request)).thenThrow(ValidationException.class);

        assertThrows(
                ValidationException.class,
                () -> createChavePixAction.createChavePix(request)
        );
        verify(createChavePixAction, times(1)).createChavePix(request);
    }

    @Test
    void deveRetornarListagemPaginadaDeChavesPixAPartirDeUmaRequestValida() {
        ChavePixFilter request = ChavePixFactory.createChavePixFilterComTodosParametros();
        Pageable pageable = PageRequest.of(0, 10);
        Page<ChavePixResponse> respostaPage = ChavePixFactory.createPageChavePixEmailResponse();

        when(readChavePixAction.getChavesPaginada(request, pageable)).thenReturn(respostaPage);

        Page<ChavePixResponse> chavePixPaginada = chavePixService.consultarChavePaginada(request, pageable);

        ChavePixResponse chaveEsperada = chavePixPaginada.getContent().get(0);
        ChavePixResponse respostaEncontrada = respostaPage.getContent().get(0);

        assertEquals(chaveEsperada.getId(), respostaEncontrada.getId());
        assertEquals(chaveEsperada.getValorChave(), respostaEncontrada.getValorChave());
        assertEquals(chaveEsperada.getTipoChave(), respostaEncontrada.getTipoChave());
        assertEquals(chaveEsperada.getNomeCorrentista(), respostaEncontrada.getNomeCorrentista());
        assertEquals(chaveEsperada.getSobrenomeCorrentista(), respostaEncontrada.getSobrenomeCorrentista());
        assertEquals(chaveEsperada.getNumeroConta(), respostaEncontrada.getNumeroConta());
        assertEquals(chaveEsperada.getNumeroAgencia(), respostaEncontrada.getNumeroAgencia());
        assertEquals(chaveEsperada.getTipoConta(), respostaEncontrada.getTipoConta());
        assertEquals(chaveEsperada.getDataHoraInclusao(), respostaEncontrada.getDataHoraInclusao());
        assertEquals(chaveEsperada.getDataHoraInativacao(), respostaEncontrada.getDataHoraInativacao());

        ChavePixResponse chaveEsperada1 = chavePixPaginada.getContent().get(1);
        ChavePixResponse respostaEncontrada1 = respostaPage.getContent().get(1);

        assertEquals(chaveEsperada1.getId(), respostaEncontrada1.getId());
        assertEquals(chaveEsperada1.getValorChave(), respostaEncontrada1.getValorChave());
        assertEquals(chaveEsperada1.getTipoChave(), respostaEncontrada1.getTipoChave());
        assertEquals(chaveEsperada1.getNomeCorrentista(), respostaEncontrada1.getNomeCorrentista());
        assertEquals(chaveEsperada1.getSobrenomeCorrentista(), respostaEncontrada1.getSobrenomeCorrentista());
        assertEquals(chaveEsperada1.getNumeroConta(), respostaEncontrada1.getNumeroConta());
        assertEquals(chaveEsperada1.getNumeroAgencia(), respostaEncontrada1.getNumeroAgencia());
        assertEquals(chaveEsperada1.getTipoConta(), respostaEncontrada1.getTipoConta());
        assertEquals(chaveEsperada1.getDataHoraInclusao(), respostaEncontrada1.getDataHoraInclusao());
        assertEquals(chaveEsperada1.getDataHoraInativacao(), respostaEncontrada1.getDataHoraInativacao());
    }

    @Test
    void deveLancarExceptionCasoAListagemPaginadaRetorneVazia() {
        ChavePixFilter request = ChavePixFactory.createChavePixFilterComTodosParametros();
        Pageable pageable = PageRequest.of(0, 10);

        Page<ChavePixResponse> respostaPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(readChavePixAction.getChavesPaginada(request, pageable)).thenThrow(RecursoNaoEncontradoException.class);
        RecursoNaoEncontradoException exception = assertThrows(
                RecursoNaoEncontradoException.class,
                () -> chavePixService.consultarChavePaginada(request, pageable)
        );

        verify(readChavePixAction, times(1)).getChavesPaginada(request, pageable);
    }

    @Test
    void deveLancarExceptionCasoOsFiltrosSejamInvalidos() {
        ChavePixFilter request = ChavePixFactory.createChavePixFilterComTodosParametros();
        Pageable pageable = PageRequest.of(0, 10);

        Page<ChavePixResponse> respostaPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(readChavePixAction.getChavesPaginada(request, pageable)).thenThrow(FiltroInvalidoException.class);
        FiltroInvalidoException exception = assertThrows(
                FiltroInvalidoException.class,
                () -> chavePixService.consultarChavePaginada(request, pageable)
        );

        verify(readChavePixAction, times(1)).getChavesPaginada(request, pageable);
    }

    @Test
    void deveRetornarChavePixPelaBuscaPorIdAPartirDeUmaRequestValida() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        ChavePixResponse chavePixResponse = ChavePixFactory.createChavePixEmailResponse(chavePix);

        when(readChavePixAction.findById(chavePix.getId())).thenReturn(chavePix);

        ChavePixResponse chavePixResponseEncontrada = chavePixService.consultarChavePorId(chavePix.getId());

        assertEquals(chavePixResponseEncontrada.getId(), chavePixResponse.getId());
        assertEquals(chavePixResponseEncontrada.getValorChave(), chavePixResponse.getValorChave());
        assertEquals(chavePixResponseEncontrada.getTipoChave(), chavePixResponse.getTipoChave());
        assertEquals(chavePixResponseEncontrada.getNomeCorrentista(), chavePixResponse.getNomeCorrentista());
        assertEquals(chavePixResponseEncontrada.getSobrenomeCorrentista(), chavePixResponse.getSobrenomeCorrentista());
        assertEquals(chavePixResponseEncontrada.getNumeroConta(), chavePixResponse.getNumeroConta());
        assertEquals(chavePixResponseEncontrada.getNumeroAgencia(), chavePixResponse.getNumeroAgencia());
        assertEquals(chavePixResponseEncontrada.getTipoConta(), chavePixResponse.getTipoConta());
    }

    @Test
    void deveLancarUmaExcecaoPelaBuscaNaoRetornarNenhumaChave() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmail();

        when(readChavePixAction.findById(chavePix.getId()))
                .thenThrow(RecursoNaoEncontradoException.class);

        RecursoNaoEncontradoException exception = assertThrows(
                RecursoNaoEncontradoException.class,
                () -> chavePixService.consultarChavePorId(chavePix.getId())
        );

        verify(readChavePixAction, times(1)).findById(chavePix.getId());
    }

    @Test
    void deveRetornarChavePixResponsePelaInativacaoPorIdAPartirDeUmaRequestValida() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        ChavePixResponse chavePixResponse = ChavePixFactory.createChavePixEmailResponse(chavePix);

        when(removeChavePixAction.removeChavePix(chavePix.getId())).thenReturn(chavePix);

        ChavePixResponse chavePixResponseEncontrada = chavePixService.inativarChavePix(chavePix.getId());

        assertEquals(chavePixResponseEncontrada.getId(), chavePixResponse.getId());
        assertEquals(chavePixResponseEncontrada.getValorChave(), chavePixResponse.getValorChave());
        assertEquals(chavePixResponseEncontrada.getTipoChave(), chavePixResponse.getTipoChave());
        assertEquals(chavePixResponseEncontrada.getNomeCorrentista(), chavePixResponse.getNomeCorrentista());
        assertEquals(chavePixResponseEncontrada.getSobrenomeCorrentista(), chavePixResponse.getSobrenomeCorrentista());
        assertEquals(chavePixResponseEncontrada.getNumeroConta(), chavePixResponse.getNumeroConta());
        assertEquals(chavePixResponseEncontrada.getNumeroAgencia(), chavePixResponse.getNumeroAgencia());
        assertEquals(chavePixResponseEncontrada.getTipoConta(), chavePixResponse.getTipoConta());
        assertEquals(chavePixResponseEncontrada.getDataHoraInclusao(), chavePixResponse.getDataHoraInclusao());
        assertEquals(chavePixResponseEncontrada.getDataHoraInativacao(), chavePixResponse.getDataHoraInativacao());
    }

    @Test
    void deveLancarExceptionCasoChavesNaoSejaEncontrada() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        ChavePixResponse chavePixResponse = ChavePixFactory.createChavePixEmailResponse(chavePix);

        when(removeChavePixAction.removeChavePix(chavePix.getId())).thenThrow(RecursoNaoEncontradoException.class);
        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> chavePixService.inativarChavePix(chavePix.getId())
        );

        verify(removeChavePixAction, times(1)).removeChavePix(chavePix.getId());
    }

    @Test
    void deveLancarExceptionCasoChavesJaInativada() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        ChavePixResponse chavePixResponse = ChavePixFactory.createChavePixEmailResponse(chavePix);

        when(removeChavePixAction.removeChavePix(chavePix.getId())).thenThrow(BusinessException.class);
        assertThrows(
                BusinessException.class,
                () -> chavePixService.inativarChavePix(chavePix.getId())
        );

        verify(removeChavePixAction, times(1)).removeChavePix(chavePix.getId());
    }

    @Test
    void deveRetornarChavePixUpdateResponsePelaAlteracaoAPartirDeUmaRequestValida() {

        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        ChavePixUpdateResponse chavePixUpdateResponse = ChavePixFactory.createChavePixEmailUpdateResponse(chavePix);
        UpdateChavePixRequest updateChavePixRequest = ChavePixFactory.updateChavePixRequest(chavePix.getId());

        when(updateChavePixAction.atualizarChavePix(updateChavePixRequest)).thenReturn(chavePix);

        ChavePixUpdateResponse chavePixResponseEncontrada = chavePixService.atualizarChavePix(updateChavePixRequest);

        assertEquals(chavePixResponseEncontrada.getId(), chavePixUpdateResponse.getId());
        assertEquals(chavePixResponseEncontrada.getValorChave(), chavePixUpdateResponse.getValorChave());
        assertEquals(chavePixResponseEncontrada.getTipoChave(), chavePixUpdateResponse.getTipoChave());
        assertEquals(chavePixResponseEncontrada.getNomeCorrentista(), chavePixUpdateResponse.getNomeCorrentista());
        assertEquals(chavePixResponseEncontrada.getSobrenomeCorrentista(), chavePixUpdateResponse.getSobrenomeCorrentista());
        assertEquals(chavePixResponseEncontrada.getNumeroConta(), chavePixUpdateResponse.getNumeroConta());
        assertEquals(chavePixResponseEncontrada.getNumeroAgencia(), chavePixUpdateResponse.getNumeroAgencia());
        assertEquals(chavePixResponseEncontrada.getTipoConta(), chavePixUpdateResponse.getTipoConta());
        assertEquals(chavePixResponseEncontrada.getDataHoraInclusao(), chavePixUpdateResponse.getDataHoraInclusao());
    }

    @Test
    void deveLancarExceptionCasoChaveInativada() {

        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        UpdateChavePixRequest updateChavePixRequest = ChavePixFactory.updateChavePixRequest(chavePix.getId());

        when(updateChavePixAction.atualizarChavePix(updateChavePixRequest)).thenThrow(BusinessException.class);

        assertThrows(
                BusinessException.class,
                () -> chavePixService.atualizarChavePix(updateChavePixRequest)
        );
    }

    @Test
    void deveLancarExceptionCasoAgenciaContaPFNovasJaTenhamCincoChaves() {

        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        UpdateChavePixRequest updateChavePixRequest = ChavePixFactory.updateChavePixRequest(chavePix.getId());

        when(updateChavePixAction.atualizarChavePix(updateChavePixRequest)).thenThrow(BusinessException.class);

        assertThrows(
                BusinessException.class,
                () -> chavePixService.atualizarChavePix(updateChavePixRequest)
        );
    }

}
