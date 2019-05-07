package drzazga.daniel.geodezja.annotations;

import drzazga.daniel.geodezja.validators.CorrectUserPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CorrectUserPasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectUserPassword {

    String message() default "error.userPasswordIsNotMatch";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
