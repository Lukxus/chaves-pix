package com.pix.chaves.services;

import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.services.chavesPix.ChavePixService;
import com.pix.chaves.services.chavesPix.CreateChavePixAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChavePixServiceTest {

    @InjectMocks
    private ChavePixService chavePixService;

    @Mock
    private CreateChavePixAction createChavePixAction;

    @Test
    void deveCriarChavePix_ComSucesso() {
        CreateChavePixRequest request = new CreateChavePixRequest();
        request.setValorChave("teste@dominio.com");

        ChavePix chaveMock = new ChavePix();
        chaveMock.setId(UUID.randomUUID());
        chaveMock.setValorChave("teste@dominio.com");

        when(createChavePixAction.createChavePix(request)).thenReturn(chaveMock);

        UUID chaveId = chavePixService.criarChavePix(request);

        assertNotNull(chaveId);
        verify(createChavePixAction, times(1)).createChavePix(request);
    }
}
