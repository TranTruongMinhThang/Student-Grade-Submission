package com.example.crudlearn.exception;

public class StudentNotEnrolledException extends RuntimeException{
    public StudentNotEnrolledException(long studentId, long courseId) {
        super("This student id: '" + studentId + "' is not enroll this course id: '" + courseId + "'");
    }
}
