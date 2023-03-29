package com.example.myproject.validation.annotation;

import com.example.myproject.validation.impl.ProfessionsConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ProfessionsConstraint.class})
public @interface Professions {

    String message() default "Professions should be STUDENT, TEACHER, MUSICIAN, DRIVER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
