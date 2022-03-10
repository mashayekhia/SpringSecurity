package ir.man.spring.security.validator;

import ir.man.spring.security.model.User;
import ir.man.spring.security.appannotations.AppValidPasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<AppValidPasswordMatches, Object> {
    @Override
    public void initialize(AppValidPasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        User user = (User) obj;
        return true;//user.getPassword().equals(user.getMatchingPassword());
    }
}
