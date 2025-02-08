package com.pix.chaves.utils.validation.valid;

import com.pix.chaves.utils.validation.validators.NumeroContaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NumeroContaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroConta {
    String message() default "O número da conta deve conter apenas números e no máximo 8 dígitos.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
