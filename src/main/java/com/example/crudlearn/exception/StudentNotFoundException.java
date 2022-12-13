package com.example.crudlearn.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(long id) {
        super("The id '" + id + "' does not exist in our records");
    }


}
