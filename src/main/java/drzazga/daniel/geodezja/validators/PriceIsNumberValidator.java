package drzazga.daniel.geodezja.validators;

import drzazga.daniel.geodezja.annotations.PriceIsNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceIsNumberValidator implements ConstraintValidator<PriceIsNumber, BigDecimal> {

    private final String priceRegex = "\\d+(\\.\\d+)?";

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        Pattern p = Pattern.compile(priceRegex);
        Matcher m = p.matcher(value.toString());
        return m.matches();
    }
}