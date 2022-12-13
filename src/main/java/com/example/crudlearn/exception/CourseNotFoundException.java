package com.example.crudlearn.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(long id) {
        super("This course id '" + id + "' does not exist in our records");
    }
}
