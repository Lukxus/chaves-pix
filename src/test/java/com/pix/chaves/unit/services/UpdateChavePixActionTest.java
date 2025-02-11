package com.pix.chaves.unit.services;

import com.pix.chaves.domain.enums.TipoContaCadastro;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.factories.ChavePixFactory;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.request.UpdateChavePixRequest;
import com.pix.chaves.services.chavesPix.CreateChavePixAction;
import com.pix.chaves.services.chavesPix.ReadChavePixAction;
import com.pix.chaves.services.chavesPix.UpdateChavePixAction;
import com.pix.chaves.services.contaCadastro.ContaCadastroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateChavePixActionTest {

    @Mock
    private ChavePixRepository repository;

    @Mock
    private ReadChavePixAction readChavePixAction;

    @InjectMocks
    private UpdateChavePixAction updateChavePixAction;

    @Mock
    private CreateChavePixAction createChavePixAction;

    @Mock
    private ContaCadastroService contaCadastroService;

    @Test
    void deveAlterarChavePixPeloId() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        UUID id = chavePix.getId();
        UpdateChavePixRequest updateChavePixRequest = ChavePixFactory.updateChavePixRequest(id);

        when(readChavePixAction.findById(id)).thenReturn(chavePix);

        ChavePix chaveEncontrada = updateChavePixAction.atualizarChavePix(updateChavePixRequest);

        chavePix.setNumeroConta(updateChavePixRequest.getNumeroConta());
        chavePix.setNumeroAgencia(updateChavePixRequest.getNumeroAgencia());
        chavePix.setNomeCorrentista(updateChavePixRequest.getNomeCorrentista());
        chavePix.setSobrenomeCorrentista(updateChavePixRequest.getSobrenomeCorrentista());
        chavePix.setTipoConta(updateChavePixRequest.getTipoConta());

        assertEquals(chavePix.getId(), chaveEncontrada.getId());
        assertEquals(chavePix.getValorChave(), chaveEncontrada.getValorChave());
        assertEquals(chavePix.getTipoChave(), chaveEncontrada.getTipoChave());
        assertEquals(chavePix.getNomeCorrentista(), chaveEncontrada.getNomeCorrentista());
        assertEquals(chavePix.getSobrenomeCorrentista(), chaveEncontrada.getSobrenomeCorrentista());
        assertEquals(chavePix.getNumeroConta(), chaveEncontrada.getNumeroConta());
        assertEquals(chavePix.getNumeroAgencia(), chaveEncontrada.getNumeroAgencia());
        assertEquals(chavePix.getTipoConta(), chaveEncontrada.getTipoConta());
        verify(readChavePixAction, times(1)).findById(id);
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveLancarExcecaoAoTentarEditarUmaChaveInativada() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmailInativada();
        UUID id = chavePix.getId();
        UpdateChavePixRequest updateChavePixRequest = ChavePixFactory.updateChavePixRequest(id);

        when(readChavePixAction.findById(id)).thenReturn(chavePix);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> updateChavePixAction.atualizarChavePix(updateChavePixRequest)
        );

        assertEquals(ErrorMessages.CHAVE_PIX_INATIVADA_NAO_PODE_SER_ALTERADA, exception.getMessage());
        verify(readChavePixAction, times(1)).findById(id);
        verify(repository, never()).save(any());

    }

    @Test
    void deveLancarExcecaoAoTentarEditarUmaChaveParaUmaAgenciaContaPFComCincoChaves() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        UUID id = chavePix.getId();
        UpdateChavePixRequest updateChavePixRequest = ChavePixFactory.updateChavePixRequest(id);

        when(readChavePixAction.findById(id)).thenReturn(chavePix);
        when(contaCadastroService.obterTipoConta(anyString(), anyString())).thenReturn(TipoContaCadastro.FISICA);

        doThrow(new BusinessException(ErrorMessages.LIMITE_CHAVES_PF))
                .when(createChavePixAction)
                .validarNumeroChaves(anyString(), anyString(), any());

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> updateChavePixAction.atualizarChavePix(updateChavePixRequest)
        );

        assertEquals(ErrorMessages.LIMITE_CHAVES_PF, exception.getMessage());

        verify(createChavePixAction, times(1))
                .validarNumeroChaves(anyString(), anyString(), any());
    }

    @Test
    void deveLancarExcecaoAoTentarEditarUmaChaveParaUmaAgenciaContaPJCom20ChavesChaves() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        UUID id = chavePix.getId();
        UpdateChavePixRequest updateChavePixRequest = ChavePixFactory.updateChavePixRequest(id);

        when(readChavePixAction.findById(id)).thenReturn(chavePix);
        when(contaCadastroService.obterTipoConta(anyString(), anyString())).thenReturn(TipoContaCadastro.JURIDICA);

        doThrow(new BusinessException(ErrorMessages.LIMITE_CHAVES_PJ))
                .when(createChavePixAction)
                .validarNumeroChaves(anyString(), anyString(), any());

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> updateChavePixAction.atualizarChavePix(updateChavePixRequest)
        );

        assertEquals(ErrorMessages.LIMITE_CHAVES_PJ, exception.getMessage());

        verify(createChavePixAction, times(1))
                .validarNumeroChaves(anyString(), anyString(), any());
    }

}
