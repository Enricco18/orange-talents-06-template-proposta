package br.com.zupacademy.enricco.proposta.validations;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@ConstraintComposition(CompositionType.OR)
@Constraint(validatedBy = {})
@CPF
@CNPJ
public @interface Document {
    String message() default "Documento deve ser um CPF ou CNPJ.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

