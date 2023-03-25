package com.example.myproject.validation.annotation;

import com.example.myproject.validation.impl.FioConstraint;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FioConstraint.class})
public @interface Fio {

    String message() default "Name must no shorter than 2 characters, " +
            "not be longer than 30 characters and it can includes " +
            "capital latin letters, lowercase latin letters, dash and space.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}