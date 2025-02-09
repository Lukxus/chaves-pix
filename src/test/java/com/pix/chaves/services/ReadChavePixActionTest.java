package com.pix.chaves.services;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.ChavePixValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.factories.ChavePixRequestFactory;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.services.chavesPix.CreateChavePixAction;
import com.pix.chaves.services.chavesPix.ReadChavePixAction;
import com.pix.chaves.utils.validation.ChavePixValidatorFactory;
import com.pix.chaves.utils.validation.valid.ChavePixValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadChavePixActionTest {

    @InjectMocks
    private CreateChavePixAction createChavePixAction;

    @InjectMocks
    private ReadChavePixAction readChavePixAction;

    @Mock
    private ChavePixRepository repository;

    @Mock
    private ChavePixValidator validator;

    @BeforeEach
    void setup() {
    }

    @Test
    void deveListarChavePixPeloId() {

        UUID chaveId = UUID.randomUUID();
        CreateChavePixRequest request = ChavePixRequestFactory.createEmailRequest();
        request.setTipoChave(TipoChave.EMAIL);

        ChavePix chavePix = new ChavePix();
        chavePix.setId(chaveId);
        chavePix.setValorChave(request.getValorChave());

        when(repository.findById(chaveId)).thenReturn(Optional.of(chavePix));

        ChavePix chaveEncontrada = readChavePixAction.findById(chaveId);

        assertNotNull(chaveEncontrada);
        assertEquals(chaveId, chaveEncontrada.getId());
        assertEquals(request.getValorChave(), chaveEncontrada.getValorChave());
        verify(repository, times(1)).findById(chaveId);
    }

//    @Test
//    void deveListarChavePixPeloTipoChaveEMAIL() {
//
//        TipoChave tipoChave = TipoChave.EMAIL;
//        List<ChavePix> response = ChavePixRequestFactory.createListChavePixEmail();
//
//        when(repository.findByTipoChave(tipoChave)).thenReturn(response);
//
//        List<ChavePixResponse> chaveEncontrada = readChavePixAction.findByTipoChave(tipoChave);
//
//        ChavePix chavePixMockada = response.get(0);
//        ChavePixResponse chavePixResposta = response.get(0);
//
//        assertNotNull(chavePixResposta);
//        assertEquals(chavePixMockada.getId(), chavePixResposta.getId());
//        assertEquals(chavePixMockada.getValorChave(), chavePixResposta.getValorChave());
//        assertEquals(chavePixMockada.getTipoChave(), chavePixResposta.getTipoChave());
//    }

//    @Test
//    void deveLancarExcecaoAoCriarChavePixEmailComValorChaveNulo() {
//        CreateChavePixRequest request = ChavePixRequestFactory.createEmailRequest();
//        request.setValorChave(null);
//        request.setTipoChave(TipoChave.EMAIL);
//        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
//            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.EMAIL)).thenReturn(validator);
//            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_EMAIL_NULO))
//                    .when(validator).validate(request.getValorChave());
//            ChavePixValidationException exception = assertThrows(
//                    ChavePixValidationException.class,
//                    () -> createChavePixAction.createChavePix(request)
//            );
//            assertEquals(ErrorMessages.CHAVE_EMAIL_NULO, exception.getMessage());
//            verify(repository, never()).save(Mockito.any());
//        }
//    }



}
