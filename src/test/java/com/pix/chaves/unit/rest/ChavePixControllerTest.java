package com.pix.chaves.unit.rest;

import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ChavePixValidationException;
import com.pix.chaves.factories.ChavePixFactory;
import com.pix.chaves.rest.controllers.ChavePixController;
import com.pix.chaves.rest.dto.request.ChavePixFilter;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.request.UpdateChavePixRequest;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.rest.dto.response.ChavePixUpdateResponse;
import com.pix.chaves.services.chavesPix.ChavePixService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChavePixControllerTest {

    @Mock
    private ChavePixService chavePixService;

    @InjectMocks
    private ChavePixController chavePixController;

    private UUID chaveId;
    private CreateChavePixRequest createChavePixRequest;
    private ChavePixResponse chavePixResponse;
    private ChavePixUpdateResponse chavePixUpdateResponse;

    @BeforeEach
    void setUp() {
        chaveId = UUID.randomUUID();
        createChavePixRequest = new CreateChavePixRequest();
        chavePixResponse = new ChavePixResponse();
        chavePixUpdateResponse = new ChavePixUpdateResponse();
    }

    @Test
    void deveCriarChavePixComSucesso() {
        when(chavePixService.criarChavePix(any(CreateChavePixRequest.class))).thenReturn(chaveId);

        ResponseEntity<UUID> response = chavePixController.criarChavePix(createChavePixRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(chaveId, response.getBody());

        verify(chavePixService, times(1)).criarChavePix(any(CreateChavePixRequest.class));
    }

    @Test
    void deveLancarExcecaoCasoRequestCriacaoInvalida() {
        CreateChavePixRequest chavePixRequest = ChavePixFactory.createAleatorioRequestInvalido();

        when(chavePixService.criarChavePix(any(CreateChavePixRequest.class)))
                .thenThrow(ChavePixValidationException.class);

        ChavePixValidationException exception = assertThrows(
                ChavePixValidationException.class,
                () -> chavePixController.criarChavePix(chavePixRequest)
        );
        verify(chavePixService, times(1)).criarChavePix(any(CreateChavePixRequest.class));
    }

    @Test
    void deveConsultarChavePorIdComSucesso() {
        when(chavePixService.consultarChavePorId(eq(chaveId))).thenReturn(chavePixResponse);

        ResponseEntity<ChavePixResponse> response = chavePixController.consultarChavePorId(chaveId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(chavePixResponse, response.getBody());

        verify(chavePixService, times(1)).consultarChavePorId(eq(chaveId));
    }

    @Test
    void deveRetornarPaginaDeChavesComSucesso() {
        Page<ChavePixResponse> pagina = new PageImpl<>(Collections.singletonList(chavePixResponse));
        ChavePixFilter chavePixFilter = ChavePixFactory.createChavePixFilterComTodosParametrosMenosDataInativacao();
        PageRequest pageable = PageRequest.of(0, 10);

        when(chavePixService.consultarChavePaginada(any(ChavePixFilter.class), any())).thenReturn(pagina);

        Page<ChavePixResponse> response = chavePixController.consultarChavesPaginada(chavePixFilter, pageable);

        assertEquals(1, response.getTotalElements());

        verify(chavePixService, times(1)).consultarChavePaginada(any(ChavePixFilter.class), any());
    }

    @Test
    void deveInativarChavePixComSucesso() {
        when(chavePixService.inativarChavePix(eq(chaveId))).thenReturn(chavePixResponse);

        ResponseEntity<ChavePixResponse> response = chavePixController.inativarChavePix(chaveId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(chavePixResponse, response.getBody());

        verify(chavePixService, times(1)).inativarChavePix(eq(chaveId));
    }

    @Test
    void deveAtualizarChavePixComSucesso() {
        UpdateChavePixRequest updateRequest = new UpdateChavePixRequest();
        when(chavePixService.atualizarChavePix(any(UpdateChavePixRequest.class))).thenReturn(chavePixUpdateResponse);

        ResponseEntity<ChavePixUpdateResponse> response = chavePixController.atualizarChavePix(updateRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(chavePixUpdateResponse, response.getBody());

        verify(chavePixService, times(1)).atualizarChavePix(any(UpdateChavePixRequest.class));
    }

    @Test
    void deveLancarExceptionChavePixComRequestUpdateParaChaveInativada() {
        UpdateChavePixRequest updateRequest = new UpdateChavePixRequest();

        when(chavePixService.atualizarChavePix(any(UpdateChavePixRequest.class)))
                .thenThrow(BusinessException.class);

        assertThrows(
                BusinessException.class,
                () -> chavePixController.atualizarChavePix(updateRequest)
        );

        verify(chavePixService, times(1)).atualizarChavePix(any(UpdateChavePixRequest.class));
    }
}
