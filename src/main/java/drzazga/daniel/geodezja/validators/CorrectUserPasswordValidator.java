package drzazga.daniel.geodezja.validators;

import drzazga.daniel.geodezja.annotations.CorrectUserPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorrectUserPasswordValidator implements ConstraintValidator <CorrectUserPassword,String> {

    private final String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\*])(?!.*\\s).{8,12}$";

    @Override
    public void initialize(CorrectUserPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern p = Pattern.compile(passwordRegex);
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
