package com.example.myproject.validation.impl;



import com.example.myproject.validation.annotation.SecurityQuestionAnswer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class SecurityQAConstraint implements ConstraintValidator<SecurityQuestionAnswer, String> {

    private static final String SECURITY_QUESTION_ANSWER_PATTERN =
            "[\\w\\sа-яА-Я!\"#$%&'()*+,\\-./:;<=>?\\\\@\\[\\]^_`{|}~]{1,50}";

    @Override
    public void initialize(SecurityQuestionAnswer constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(SECURITY_QUESTION_ANSWER_PATTERN))
                .orElse(false);
    }
}
