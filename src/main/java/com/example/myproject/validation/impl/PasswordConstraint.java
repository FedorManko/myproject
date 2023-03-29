package com.example.myproject.validation.impl;

import com.example.myproject.validation.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PasswordConstraint implements ConstraintValidator<Password, String> {

    private static final String PASSWORD_PATTERN =
            "(?=.*\\d)" +       // at least one digit
            "(?=.*[a-z])" +     // at least one lowercase latin letter
            "(?=.*[A-Z])" +     // at least one capital latin letter
            "(?=.*[\\s!\"#$%&'()*+,-./:;<=>?\\\\@\\[\\]^_`{|}~])" +      // at least one auxiliary symbol
            "[\\w\\s!\"#$%&'()*+,\\-./:;<=>?\\\\@\\[\\]^_`{|}~]{6,20}";    // all available symbols, password length 6-20 symbols

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(PASSWORD_PATTERN))
                .orElse(false);
    }
}