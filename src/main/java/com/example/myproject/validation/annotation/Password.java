package com.example.myproject.validation.annotation;

import com.example.myproject.validation.impl.PasswordConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordConstraint.class})
public @interface Password {

    String message() default "Password must be 6-20 characters long and includes at least " +
            "one symbol from capital latin letters, lowercase latin letters, numbers, auxiliary.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}