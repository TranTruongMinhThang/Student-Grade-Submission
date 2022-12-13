package com.example.crudlearn.exception;

public class GradeNotFoundException extends RuntimeException{

    public GradeNotFoundException(long studentId, long courseId) {
        super("This grade with student id '" + studentId + "' and course id '" + courseId + "' does not exist in our records");
    }
}
