package com.example.myproject.validation.impl;


import com.example.myproject.validation.annotation.JwtToken;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class JwtTokenConstraint implements ConstraintValidator<JwtToken, String> {

    private static final String JWT_TOKEN_PATTERN = "^[\\w-]+\\.[\\w-]+\\.[\\w-]+$";

    @Override
    public void initialize(JwtToken constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(JWT_TOKEN_PATTERN))
                .orElse(false);
    }
}
