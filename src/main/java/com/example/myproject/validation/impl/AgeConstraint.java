package com.example.myproject.validation.impl;

import com.example.myproject.validation.annotation.Age;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class AgeConstraint implements ConstraintValidator<Age, Integer> {

    private static final String NAME_PATTERN = "\\d{2}";

    @Override
    public void initialize(Age constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer ageOfPerson, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.of(ageOfPerson)
                .filter(age -> age > 18 && age < 60)
                .map(age -> age.toString().matches(NAME_PATTERN))
                .orElse(false);
    }

}
