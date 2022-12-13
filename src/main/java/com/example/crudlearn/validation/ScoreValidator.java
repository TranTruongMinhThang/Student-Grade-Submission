package com.example.crudlearn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ScoreValidator implements ConstraintValidator<Score,String> {
    List<String> scores = Arrays.asList(
            "A", "A+", "A-",
            "B", "B+", "B-",
            "C", "C+", "C-",
            "D", "D+", "D-",
            "F"
    );
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return scores.contains(s);
    }
}
