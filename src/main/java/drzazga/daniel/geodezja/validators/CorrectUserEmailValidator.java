package drzazga.daniel.geodezja.validators;

import drzazga.daniel.geodezja.annotations.CorrectUserEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorrectUserEmailValidator implements ConstraintValidator <CorrectUserEmail, String> {

    private final String emailRegex = "^[a-zA-z0-9]+[\\._a-zA-Z0-9]*@[a-zA-Z0-9]+{2,}\\.[a-zA-Z]{2,}[\\.a-zA-Z0-9]*$";

    @Override
    public void initialize(CorrectUserEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern p = Pattern.compile(emailRegex);
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
