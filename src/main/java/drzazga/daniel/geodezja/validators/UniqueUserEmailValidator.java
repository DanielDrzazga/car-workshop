package drzazga.daniel.geodezja.validators;

import drzazga.daniel.geodezja.annotations.UniqueUserEmail;
import drzazga.daniel.geodezja.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator <UniqueUserEmail, String> {

    private final UserService userService;

    @Autowired
    public UniqueUserEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUserEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        return userService.isEmailExist(value);
        return true;
    }
}
