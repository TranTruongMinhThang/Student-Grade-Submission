package com.example.crudlearn.validation;

import com.example.crudlearn.entity.Student;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface Age {
    String message() default "Invalid Age";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
