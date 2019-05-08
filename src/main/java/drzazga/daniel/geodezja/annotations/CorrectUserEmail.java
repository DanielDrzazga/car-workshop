package drzazga.daniel.geodezja.annotations;

import drzazga.daniel.geodezja.validators.CorrectUserEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CorrectUserEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectUserEmail {

    String message() default "{error.userEmailIsNotMatch}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
