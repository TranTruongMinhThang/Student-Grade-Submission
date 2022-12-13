package com.example.crudlearn.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long id) {
        super("This user with id: '" + id + "' does not exist in our records");
    }
}
