package com.pix.chaves.services;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.exception.BusinessException;
import com.pix.chaves.exception.ChavePixValidationException;
import com.pix.chaves.exception.ErrorMessages;
import com.pix.chaves.factories.ChavePixRequestFactory;
import com.pix.chaves.factories.TestConstants;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.repository.ChavePixRepository;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.services.chavesPix.CreateChavePixAction;
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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateChavePixActionTest {

    @InjectMocks
    private CreateChavePixAction createChavePixAction;

    @Mock
    private ChavePixRepository repository;

    @Mock
    private ChavePixValidator validator;

    @BeforeEach
    void setup() {
    }

    //EMAIL
    @Test
    void deveCriarChavePixEMAILComDadosValidos() {
        CreateChavePixRequest request = ChavePixRequestFactory.createEmailRequest();
        request.setTipoChave(TipoChave.EMAIL);

        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class);
             MockedStatic<ChavePixMapper> mapperMock = Mockito.mockStatic(ChavePixMapper.class)) {

            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.EMAIL))
                    .thenReturn(validator);
            doNothing().when(validator).validate(anyString());

            when(repository.existsByValorChave(request.getValorChave())).thenReturn(false);

            ChavePix chavePix = new ChavePix();
            chavePix.setValorChave(request.getValorChave());

            mapperMock.when(() -> ChavePixMapper.toEntity(request)).thenReturn(chavePix);

            when(repository.save(any(ChavePix.class))).thenAnswer(invocation -> {
                ChavePix entidade = invocation.getArgument(0);
                entidade.setId(UUID.randomUUID());
                return entidade;
            });

            ChavePix chaveCriada = createChavePixAction.createChavePix(request);

            assertNotNull(chaveCriada.getId());
            assertEquals(request.getValorChave(), chaveCriada.getValorChave());
            verify(repository, times(1)).existsByValorChave(request.getValorChave());
            verify(repository, times(1)).save(any(ChavePix.class));
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixEmailComValorChaveNulo() {
        CreateChavePixRequest request = ChavePixRequestFactory.createEmailRequest();
        request.setValorChave(null);
        request.setTipoChave(TipoChave.EMAIL);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.EMAIL)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_EMAIL_NULO))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_EMAIL_NULO, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixEmailComValorChaveSemAt() {
        CreateChavePixRequest request = ChavePixRequestFactory.createEmailRequest();
        request.setValorChave("emaildominio.com");
        request.setTipoChave(TipoChave.EMAIL);

        try (MockedStatic<com.pix.chaves.utils.validation.ChavePixValidatorFactory> validatorFactoryMock =
                     Mockito.mockStatic(com.pix.chaves.utils.validation.ChavePixValidatorFactory.class)) {

            validatorFactoryMock.when(() -> com.pix.chaves.utils.validation.ChavePixValidatorFactory.getValidator(TipoChave.EMAIL))
                    .thenReturn(validator);

            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_EMAIL_CARACTER_AT))
                    .when(validator).validate(request.getValorChave());

            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_EMAIL_CARACTER_AT, exception.getMessage());

            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixEmailComValorChaveComMaisCaracteresQueOLimite() {
        CreateChavePixRequest request = ChavePixRequestFactory.createEmailRequest();
        request.setValorChave(TestConstants.EMAIL_COM_TAMANHO_MAIOR_QUE_O_LIMITE);
        request.setTipoChave(TipoChave.EMAIL);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.EMAIL)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_EMAIL_NUMERO_MAXIMO_CARACTERES))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_EMAIL_NUMERO_MAXIMO_CARACTERES, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    //CELULAR

    @Test
    void deveCriarChavePixCelularComoDadosValidos() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setTipoChave(TipoChave.CELULAR);

        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class);
             MockedStatic<ChavePixMapper> mapperMock = Mockito.mockStatic(ChavePixMapper.class)) {

            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CELULAR))
                    .thenReturn(validator);
            doNothing().when(validator).validate(anyString());

            when(repository.existsByValorChave(request.getValorChave())).thenReturn(false);

            ChavePix chavePix = new ChavePix();
            chavePix.setValorChave(request.getValorChave());

            mapperMock.when(() -> ChavePixMapper.toEntity(request)).thenReturn(chavePix);

            when(repository.save(any(ChavePix.class))).thenAnswer(invocation -> {
                ChavePix entidade = invocation.getArgument(0);
                entidade.setId(UUID.randomUUID());
                return entidade;
            });

            ChavePix chaveCriada = createChavePixAction.createChavePix(request);

            assertNotNull(chaveCriada.getId());
            assertEquals(request.getValorChave(), chaveCriada.getValorChave());
            verify(repository, times(1)).existsByValorChave(request.getValorChave());
            verify(repository, times(1)).save(any(ChavePix.class));
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCelularComValorChaveNulo() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(null);
        request.setTipoChave(TipoChave.CELULAR);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CELULAR)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CELULAR_NULA))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CELULAR_NULA, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCelularComValorChaveSemMaisNoInicio() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CELULAR_SEM_MAIS_NO_COMECO);
        request.setTipoChave(TipoChave.CELULAR);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CELULAR)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CELULAR_CARACTERES_MAIS))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CELULAR_CARACTERES_MAIS, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCelularComValorChaveMaiorQueOLimite() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CELULAR_COM_TAMANHO_MAIOR_QUE_O_LIMITE);
        request.setTipoChave(TipoChave.CELULAR);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CELULAR)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CELULAR_FORMATO_INVALIDO))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CELULAR_FORMATO_INVALIDO, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCelularComValorChaveAlfanumerico() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CELULAR_COM_ALFANUMERICO);
        request.setTipoChave(TipoChave.CELULAR);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CELULAR)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CELULAR_FORMATO_INVALIDO))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CELULAR_FORMATO_INVALIDO, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    // CPF

    @Test
    void deveCriarChavePixCPFComoDadosValidos() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCpfRequest();
        request.setTipoChave(TipoChave.CPF);

        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class);
             MockedStatic<ChavePixMapper> mapperMock = Mockito.mockStatic(ChavePixMapper.class)) {

            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CPF))
                    .thenReturn(validator);
            doNothing().when(validator).validate(anyString());

            when(repository.existsByValorChave(request.getValorChave())).thenReturn(false);

            ChavePix chavePix = new ChavePix();
            chavePix.setValorChave(request.getValorChave());

            mapperMock.when(() -> ChavePixMapper.toEntity(request)).thenReturn(chavePix);

            when(repository.save(any(ChavePix.class))).thenAnswer(invocation -> {
                ChavePix entidade = invocation.getArgument(0);
                entidade.setId(UUID.randomUUID());
                return entidade;
            });

            ChavePix chaveCriada = createChavePixAction.createChavePix(request);

            assertNotNull(chaveCriada.getId());
            assertEquals(request.getValorChave(), chaveCriada.getValorChave());
            verify(repository, times(1)).existsByValorChave(request.getValorChave());
            verify(repository, times(1)).save(any(ChavePix.class));
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCPFComValorChaveNulo() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(null);
        request.setTipoChave(TipoChave.CPF);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CPF)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CPF_VAZIO_NULO))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CPF_VAZIO_NULO, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCPFComValorChaveAlfanumerico() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CPF_ALFANUMERICO);
        request.setTipoChave(TipoChave.CPF);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CPF)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CPF_DIGITOS_NUMERICOS))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CPF_DIGITOS_NUMERICOS, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCPFComValorChaveMaiorQueOLimite() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CPF_COM_TAMANHO_MAIOR_QUE_O_LIMITE);
        request.setTipoChave(TipoChave.CPF);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CPF)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CPF_NUMERO_MAXIMO_CARACTERES))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CPF_NUMERO_MAXIMO_CARACTERES, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCPFComValorChaveMenorQueOLimite() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CPF_COM_TAMANHO_MENOR_QUE_O_LIMITE);
        request.setTipoChave(TipoChave.CPF);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CPF)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CPF_NUMERO_MINIMO_CARACTERES))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CPF_NUMERO_MINIMO_CARACTERES, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCPFComValorChaveInvalido() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CPF_VALOR_INVALIDO);
        request.setTipoChave(TipoChave.CPF);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CPF)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CPF_FORMATO_INVALIDO))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CPF_FORMATO_INVALIDO, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    // CNPJ

    @Test
    void deveCriarChavePixCNPJComoDadosValidos() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCNPJRequest();
        request.setTipoChave(TipoChave.CNPJ);

        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class);
             MockedStatic<ChavePixMapper> mapperMock = Mockito.mockStatic(ChavePixMapper.class)) {

            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CNPJ))
                    .thenReturn(validator);
            doNothing().when(validator).validate(anyString());

            when(repository.existsByValorChave(request.getValorChave())).thenReturn(false);

            ChavePix chavePix = new ChavePix();
            chavePix.setValorChave(request.getValorChave());

            mapperMock.when(() -> ChavePixMapper.toEntity(request)).thenReturn(chavePix);

            when(repository.save(any(ChavePix.class))).thenAnswer(invocation -> {
                ChavePix entidade = invocation.getArgument(0);
                entidade.setId(UUID.randomUUID());
                return entidade;
            });

            ChavePix chaveCriada = createChavePixAction.createChavePix(request);

            assertNotNull(chaveCriada.getId());
            assertEquals(request.getValorChave(), chaveCriada.getValorChave());
            verify(repository, times(1)).existsByValorChave(request.getValorChave());
            verify(repository, times(1)).save(any(ChavePix.class));
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCNPJComValorChaveNulo() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(null);
        request.setTipoChave(TipoChave.CNPJ);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CNPJ)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CNPJ_VAZIO_NULO))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CNPJ_VAZIO_NULO, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCNPJComValorChaveAlfanumerico() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CNPJ_ALFANUMERICO);
        request.setTipoChave(TipoChave.CNPJ);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CNPJ)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CNPJ_DIGITOS_NUMERICOS))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CNPJ_DIGITOS_NUMERICOS, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCNPJComValorChaveMaiorQueOLimite() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CNPJ_COM_TAMANHO_MAIOR_QUE_O_LIMITE);
        request.setTipoChave(TipoChave.CNPJ);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CNPJ)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CNPJ_NUMERO_MAXIMO_CARACTERES))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CNPJ_NUMERO_MAXIMO_CARACTERES, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCNPJComValorChaveMenorQueOLimite() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CNPJ_COM_TAMANHO_MENOR_QUE_O_LIMITE);
        request.setTipoChave(TipoChave.CNPJ);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CNPJ)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CNPJ_NUMERO_MINIMO_CARACTERES))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CNPJ_NUMERO_MINIMO_CARACTERES, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixCNPJComValorChaveInvalido() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.CNPJ_INVALIDO);
        request.setTipoChave(TipoChave.CNPJ);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.CNPJ)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_CNPJ_FORMATO_INVALIDO))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_CNPJ_FORMATO_INVALIDO, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    // ALEATORIO
    @Test
    void deveCriarChavePixAleatorioComoDadosValidos() {
        CreateChavePixRequest request = ChavePixRequestFactory.createAleatorioRequest();
        request.setTipoChave(TipoChave.ALEATORIA);

        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class);
             MockedStatic<ChavePixMapper> mapperMock = Mockito.mockStatic(ChavePixMapper.class)) {

            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.ALEATORIA))
                    .thenReturn(validator);
            doNothing().when(validator).validate(anyString());

            when(repository.existsByValorChave(request.getValorChave())).thenReturn(false);

            ChavePix chavePix = new ChavePix();
            chavePix.setValorChave(request.getValorChave());

            mapperMock.when(() -> ChavePixMapper.toEntity(request)).thenReturn(chavePix);

            when(repository.save(any(ChavePix.class))).thenAnswer(invocation -> {
                ChavePix entidade = invocation.getArgument(0);
                entidade.setId(UUID.randomUUID());
                return entidade;
            });

            ChavePix chaveCriada = createChavePixAction.createChavePix(request);

            assertNotNull(chaveCriada.getId());
            assertEquals(request.getValorChave(), chaveCriada.getValorChave());
            verify(repository, times(1)).existsByValorChave(request.getValorChave());
            verify(repository, times(1)).save(any(ChavePix.class));
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixAleatorioComValorChaveNulo() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(null);
        request.setTipoChave(TipoChave.ALEATORIA);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.ALEATORIA)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_ALEATORIA_VAZIA_NULA))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_ALEATORIA_VAZIA_NULA, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarChavePixAleatorioComValorChaveMaiorQueOLimite() {
        CreateChavePixRequest request = ChavePixRequestFactory.createCelularRequest();
        request.setValorChave(TestConstants.ALEATORIA_MAIOR_QUE_O_LIMITE);
        request.setTipoChave(TipoChave.ALEATORIA);
        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class)) {
            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.ALEATORIA)).thenReturn(validator);
            doThrow(new ChavePixValidationException(ErrorMessages.CHAVE_ALEATORIA_CARACTERES_MAXIMOS))
                    .when(validator).validate(request.getValorChave());
            ChavePixValidationException exception = assertThrows(
                    ChavePixValidationException.class,
                    () -> createChavePixAction.createChavePix(request)
            );
            assertEquals(ErrorMessages.CHAVE_ALEATORIA_CARACTERES_MAXIMOS, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

    @Test
    void deveLancarExcecao_QuandoChaveJaExistir() {
        CreateChavePixRequest request = ChavePixRequestFactory.createAleatorioRequest();
        request.setTipoChave(TipoChave.ALEATORIA);

        try (MockedStatic<ChavePixValidatorFactory> validatorFactoryMock = Mockito.mockStatic(ChavePixValidatorFactory.class);
             MockedStatic<ChavePixMapper> mapperMock = Mockito.mockStatic(ChavePixMapper.class)) {

            validatorFactoryMock.when(() -> ChavePixValidatorFactory.getValidator(TipoChave.ALEATORIA))
                    .thenReturn(validator);
            doNothing().when(validator).validate(anyString());

            when(repository.existsByValorChave(request.getValorChave())).thenReturn(true);

            BusinessException exception = assertThrows(
                    BusinessException.class,
                    () -> createChavePixAction.createChavePix(request)
            );

            assertEquals(ErrorMessages.CHAVE_PIX_JA_CADASTRADA, exception.getMessage());
            verify(repository, never()).save(Mockito.any());
        }
    }

}
