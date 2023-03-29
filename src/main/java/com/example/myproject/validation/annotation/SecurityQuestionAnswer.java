package com.example.myproject.validation.annotation;



import com.example.myproject.validation.impl.SecurityQAConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SecurityQAConstraint.class})
public @interface SecurityQuestionAnswer {

    String message() default "Security question or answer must contain at least 1 " +
            "and no more than 50 characters consisting of uppercase or lowercase Latin " +
            "or Cyrillic letters, numbers, auxiliary characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
