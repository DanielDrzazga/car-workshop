package drzazga.daniel.geodezja.annotations;

import drzazga.daniel.geodezja.validators.PriceIsNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PriceIsNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceIsNumber {

    String message() default "{error.partPriceIsNotMatch}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}