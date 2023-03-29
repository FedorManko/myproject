package com.example.myproject.validation.impl;

import com.example.myproject.entity.Profession;
import com.example.myproject.validation.annotation.Professions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProfessionsConstraint implements ConstraintValidator<Professions, String> {

    private static final String NAME_PATTERN = "[a-zA-Z\\s]{2,10}";
    private static final List<String> professions = List.of(Arrays.stream(Profession.values()).map(Enum::name).toArray(String[]::new));

    @Override
    public void initialize(Professions constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String profession, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(profession)
                .filter(professions::contains)
                .map(prof -> prof.matches(NAME_PATTERN))
                .orElse(false);
    }
}
