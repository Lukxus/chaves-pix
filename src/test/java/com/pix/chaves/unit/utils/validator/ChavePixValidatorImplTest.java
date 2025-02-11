package com.pix.chaves.unit.utils.validator;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.exception.ValidationException;
import com.pix.chaves.factories.TestConstants;
import com.pix.chaves.utils.validation.ChavePixValidatorFactory;
import com.pix.chaves.utils.validation.validators.CNPJValidator;
import com.pix.chaves.utils.validation.validators.CPFValidator;
import com.pix.chaves.utils.validation.validators.CelularValidator;
import com.pix.chaves.utils.validation.validators.ChaveAleatoriaValidator;
import com.pix.chaves.utils.validation.validators.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChavePixValidatorImplTest {


    // EMAIL
    @Test
    public void testValidate_ChaveEmailComValorValido_NaoLancaExcecao() {
        EmailValidator emailValidator = (EmailValidator) ChavePixValidatorFactory.getValidator(TipoChave.EMAIL);
        assertDoesNotThrow(() -> emailValidator.validate(TestConstants.EMAIL));
    }

    @Test
    public void testValidate_ChaveEmailComValorNulo() {
        EmailValidator emailValidator = (EmailValidator) ChavePixValidatorFactory.getValidator(TipoChave.EMAIL);
        assertThrows(ValidationException.class, () -> emailValidator.validate(null));
    }

    @Test
    public void testValidate_ChaveEmailComValorChaveSemAt() {
        EmailValidator emailValidator = (EmailValidator) ChavePixValidatorFactory.getValidator(TipoChave.EMAIL);
        assertThrows(ValidationException.class, () -> emailValidator.validate(TestConstants.EMAIL_SEM_AT));
    }

    @Test
    public void testValidate_ChaveEmailComValorChaveMaiorQueOLimite() {
        EmailValidator emailValidator = (EmailValidator) ChavePixValidatorFactory.getValidator(TipoChave.EMAIL);
        assertThrows(ValidationException.class, () -> emailValidator.validate(TestConstants.EMAIL_COM_TAMANHO_MAIOR_QUE_O_LIMITE));
    }

    // CELULAR

    @Test
    public void testValidate_ChaveCeluarComValorValido_NaoLancaExcecao() {
        CelularValidator celularValidator = (CelularValidator) ChavePixValidatorFactory.getValidator(TipoChave.CELULAR);
        assertDoesNotThrow(() -> celularValidator.validate(TestConstants.CELULAR));
    }

    @Test
    public void testValidate_ChaveCelularComValorChaveNulo() {
        CelularValidator celularValidator = (CelularValidator) ChavePixValidatorFactory.getValidator(TipoChave.CELULAR);
        assertThrows(ValidationException.class, () -> celularValidator.validate(null));
    }

    @Test
    public void testValidate_ChaveCelularComValorChaveSemMaisNoInicio() {
        CelularValidator celularValidator = (CelularValidator) ChavePixValidatorFactory.getValidator(TipoChave.CELULAR);
        assertThrows(ValidationException.class, () -> celularValidator.validate(TestConstants.CELULAR_SEM_MAIS_NO_COMECO));
    }

    @Test
    public void testValidate_ChaveCelularComValorChaveMaiorQueOLimite() {
        CelularValidator celularValidator = (CelularValidator) ChavePixValidatorFactory.getValidator(TipoChave.CELULAR);
        assertThrows(ValidationException.class, () -> celularValidator.validate(TestConstants.CELULAR_COM_TAMANHO_MAIOR_QUE_O_LIMITE));
    }

    @Test
    public void testValidate_ChaveCelularComValorChaveComAlfanumerico() {
        CelularValidator celularValidator = (CelularValidator) ChavePixValidatorFactory.getValidator(TipoChave.CELULAR);
        assertThrows(ValidationException.class, () -> celularValidator.validate(TestConstants.CELULAR_COM_ALFANUMERICO));
    }

    // CPF

    @Test
    public void testValidate_ChaveCPFComValorValido_NaoLancaExcecao() {
        CPFValidator cpfValidator = (CPFValidator) ChavePixValidatorFactory.getValidator(TipoChave.CPF);
        assertDoesNotThrow(() -> cpfValidator.validate(TestConstants.CPF));
    }

    @Test
    public void testValidate_ChaveCPFComValorNull() {
        CPFValidator cpfValidator = (CPFValidator) ChavePixValidatorFactory.getValidator(TipoChave.CPF);
        assertThrows(ValidationException.class, () -> cpfValidator.validate(null));
    }

    @Test
    public void testValidate_ChaveCPFComValorAlfanumerico() {
        CPFValidator cpfValidator = (CPFValidator) ChavePixValidatorFactory.getValidator(TipoChave.CPF);
        assertThrows(ValidationException.class, () -> cpfValidator.validate(TestConstants.CPF_ALFANUMERICO));
    }

    @Test
    public void testValidate_ChaveCPFComValorMaiorQueOLimite() {
        CPFValidator cpfValidator = (CPFValidator) ChavePixValidatorFactory.getValidator(TipoChave.CPF);
        assertThrows(ValidationException.class, () -> cpfValidator.validate(TestConstants.CPF_COM_TAMANHO_MAIOR_QUE_O_LIMITE));
    }

    @Test
    public void testValidate_ChaveCPFComValorMenorrQueOLimite() {
        CPFValidator cpfValidator = (CPFValidator) ChavePixValidatorFactory.getValidator(TipoChave.CPF);
        assertThrows(ValidationException.class, () -> cpfValidator.validate(TestConstants.CPF_COM_TAMANHO_MENOR_QUE_O_LIMITE));
    }

    @Test
    public void testValidate_ChaveCPFComValorInvalido() {
        CPFValidator cpfValidator = (CPFValidator) ChavePixValidatorFactory.getValidator(TipoChave.CPF);
        assertThrows(ValidationException.class, () -> cpfValidator.validate(TestConstants.CPF_VALOR_INVALIDO));
    }

    // CNPJ

    @Test
    public void testValidate_ChaveCNPJComValorValido_NaoLancaExcecao() {
        CNPJValidator cnpjValidator = (CNPJValidator) ChavePixValidatorFactory.getValidator(TipoChave.CNPJ);
        assertDoesNotThrow(() -> cnpjValidator.validate(TestConstants.CNPJ));
    }

    @Test
    public void testValidate_ChaveCNPJComValorNulo() {
        CNPJValidator cnpjValidator = (CNPJValidator) ChavePixValidatorFactory.getValidator(TipoChave.CNPJ);
        assertThrows(ValidationException.class, () -> cnpjValidator.validate(null));
    }

    @Test
    public void testValidate_ChaveCNPJComValorAlfanumerico() {
        CNPJValidator cnpjValidator = (CNPJValidator) ChavePixValidatorFactory.getValidator(TipoChave.CNPJ);
        assertThrows(ValidationException.class, () -> cnpjValidator.validate(TestConstants.CNPJ_ALFANUMERICO));
    }

    @Test
    public void testValidate_ChaveCNPJComValorMaiorQueOLimite() {
        CNPJValidator cnpjValidator = (CNPJValidator) ChavePixValidatorFactory.getValidator(TipoChave.CNPJ);
        assertThrows(ValidationException.class, () -> cnpjValidator.validate(TestConstants.CNPJ_COM_TAMANHO_MAIOR_QUE_O_LIMITE));
    }

    @Test
    public void testValidate_ChaveCNPJComValorMenorrQueOLimite() {
        CNPJValidator cnpjValidator = (CNPJValidator) ChavePixValidatorFactory.getValidator(TipoChave.CNPJ);
        assertThrows(ValidationException.class, () -> cnpjValidator.validate(TestConstants.CNPJ_COM_TAMANHO_MENOR_QUE_O_LIMITE));
    }

    @Test
    public void testValidate_ChaveCNPJComValorInvalido() {
        CNPJValidator cnpjValidator = (CNPJValidator) ChavePixValidatorFactory.getValidator(TipoChave.CNPJ);
        assertThrows(ValidationException.class, () -> cnpjValidator.validate(TestConstants.CNPJ_COM_TAMANHO_MENOR_QUE_O_LIMITE));
    }

    // ALEATORIA

    @Test
    public void testValidate_ChaveAleatoriaComValorValido_NaoLancaExcecao() {
        ChaveAleatoriaValidator aleatoriaValidator = (ChaveAleatoriaValidator) ChavePixValidatorFactory.getValidator(TipoChave.ALEATORIA);
        assertDoesNotThrow(() -> aleatoriaValidator.validate(TestConstants.ALEATORIA));
    }

    @Test
    public void testValidate_ChaveAleatoriaComValorNulo() {
        ChaveAleatoriaValidator aleatoriaValidator = (ChaveAleatoriaValidator) ChavePixValidatorFactory.getValidator(TipoChave.ALEATORIA);
        assertThrows(ValidationException.class,() -> aleatoriaValidator.validate(null));
    }

    @Test
    public void testValidate_ChaveAleatoriaComValorMaiorQueOLimite() {
        ChaveAleatoriaValidator aleatoriaValidator = (ChaveAleatoriaValidator) ChavePixValidatorFactory.getValidator(TipoChave.ALEATORIA);
        assertThrows(ValidationException.class,() -> aleatoriaValidator.validate(TestConstants.ALEATORIA_MAIOR_QUE_O_LIMITE));
    }

}
