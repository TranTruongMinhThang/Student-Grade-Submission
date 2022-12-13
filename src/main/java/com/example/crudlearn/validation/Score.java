package com.example.crudlearn.validation;

import org.springframework.context.annotation.Scope;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScoreValidator.class)
public @interface Score {
    String message() default "Invalid Score";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
