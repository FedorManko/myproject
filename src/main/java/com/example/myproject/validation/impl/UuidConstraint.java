package com.example.myproject.validation.impl;

import com.example.myproject.validation.annotation.Uuid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.UUID;

public class UuidConstraint implements ConstraintValidator<Uuid, UUID> {

    private static final String UUID_PATTERN = "^[\\da-fA-F]{8}-[\\da-fA-F]{4}-[\\da-fA-F]{4}-[\\da-fA-F]{4}-[\\da-fA-F]{12}$";

    @Override
    public void initialize(Uuid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext constraintValidatorContext) {
        String stringUuid = uuid.toString();
        return Optional.ofNullable(stringUuid)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(UUID_PATTERN))
                .orElse(false);
    }

}
