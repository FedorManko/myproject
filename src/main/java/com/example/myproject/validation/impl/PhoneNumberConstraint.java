package com.example.myproject.validation.impl;


import com.example.myproject.validation.annotation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PhoneNumberConstraint implements ConstraintValidator<PhoneNumber, String> {

    private static final String PHONE_PATTERN = "\\+37529\\d{7}";

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(PHONE_PATTERN))
                .orElse(false);
    }
}
