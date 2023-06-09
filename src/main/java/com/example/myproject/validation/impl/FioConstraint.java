package com.example.myproject.validation.impl;


import com.example.myproject.repository.ClientRepository;
import com.example.myproject.validation.annotation.Fio;
import lombok.RequiredArgsConstructor;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
@RequiredArgsConstructor
public class FioConstraint implements ConstraintValidator<Fio, String> {

    private static final String NAME_PATTERN = "[a-zA-Zа-яА-Я\\s-]{2,30}";

    private final ClientRepository clientRepository;

    @Override
    public void initialize(Fio constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(NAME_PATTERN))
                .orElse(false);
    }
}
