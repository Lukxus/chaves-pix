package com.pix.chaves.unit.services;

import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.exception.RecursoNaoEncontradoException;
import com.pix.chaves.factories.ChavePixFactory;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.services.chavesPix.ReadChavePixAction;
import com.pix.chaves.services.chavesPix.RemoveChavePixAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoveChavePixActionTest {

    @Mock
    private ChavePixRepository repository;

    @Mock
    private ReadChavePixAction readChavePixAction;

    @InjectMocks
    private RemoveChavePixAction removeChavePixAction;

    @Test
    void deveInativarChavePixPeloId() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmail();
        UUID id = chavePix.getId();

        when(readChavePixAction.findById(id)).thenReturn(chavePix);

        ChavePix chaveEncontrada = removeChavePixAction.removeChavePix(id);

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
    void deveLancarExcecaoAoTentarInativarUmaChaveQueJaEstaInativada() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmailInativada();
        UUID id = chavePix.getId();

        when(readChavePixAction.findById(id)).thenReturn(chavePix);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> removeChavePixAction.removeChavePix(id)
        );

        assertEquals(ErrorMessages.CHAVE_PIX_JA_INATIVADA, exception.getMessage());
        verify(readChavePixAction, times(1)).findById(id);
        verify(repository, never()).save(any());

    }

    @Test
    void deveLancarExcecaoAoTentarInativarUmaChaveQueNaoExiste() {
        ChavePix chavePix = ChavePixFactory.createChavePixEmailInativada();
        UUID id = chavePix.getId();

        when(readChavePixAction.findById(id)).thenThrow(new RecursoNaoEncontradoException(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA));

        RecursoNaoEncontradoException exception = assertThrows(
                RecursoNaoEncontradoException.class,
                () -> removeChavePixAction.removeChavePix(id)
        );

        assertEquals(ErrorMessages.CHAVE_PIX_NAO_ENCONTRADA, exception.getMessage());
        verify(readChavePixAction, times(1)).findById(id);
        verify(repository, never()).save(any());

    }

}
