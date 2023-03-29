package com.example.myproject.validation.annotation;



import com.example.myproject.validation.impl.JwtTokenConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {JwtTokenConstraint.class})
public @interface JwtToken {

    String message() default "Invalid JWT Token";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
