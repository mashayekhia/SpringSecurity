package ir.man.spring.security.validator;

import ir.man.spring.security.appannotations.AppValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<AppValidPassword,String> {
    @Override
    public void initialize(AppValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        return false;
    }
}
