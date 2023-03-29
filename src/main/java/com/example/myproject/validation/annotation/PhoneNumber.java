package com.example.myproject.validation.annotation;


import com.example.myproject.validation.impl.PhoneNumberConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PhoneNumberConstraint.class})
public @interface PhoneNumber {

    String message() default "Phone number should be 13 symbols length and starts with '+37529";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}