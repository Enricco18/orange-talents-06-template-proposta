package br.com.zupacademy.enricco.proposta.validations;

import br.com.zupacademy.enricco.proposta.utils.crypto.Encryptor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValueValidator.class)
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UniqueValue {
    String message() default "Valor deve ser único no banco de dados.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();

    boolean encryptor();
}
