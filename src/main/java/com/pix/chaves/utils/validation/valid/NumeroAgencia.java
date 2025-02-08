package com.pix.chaves.utils.validation.valid;

import com.pix.chaves.utils.validation.validators.NumeroAgenciaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NumeroAgenciaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroAgencia {
    String message() default "O número da agência deve conter apenas números e no máximo 4 dígitos.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
