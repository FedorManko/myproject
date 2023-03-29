package com.example.myproject.validation.annotation;

import com.example.myproject.validation.impl.AgeConstraint;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AgeConstraint.class})
public @interface Age {

    String message() default "Age should be greater the 18 but less the 60";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
