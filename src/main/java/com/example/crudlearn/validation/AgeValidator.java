package com.example.crudlearn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        long age = LocalDate.from(localDate).until(LocalDate.now(), ChronoUnit.YEARS);
        return age > 3L;
    }
}
