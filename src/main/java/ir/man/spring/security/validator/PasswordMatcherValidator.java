package ir.man.spring.security.validator;

import ir.man.spring.security.appannotations.AppValidPasswordMatches;
import ir.man.spring.security.model.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<AppValidPasswordMatches, Object> {
    @Override
    public void initialize(AppValidPasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
