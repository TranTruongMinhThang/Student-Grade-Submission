package com.example.crudlearn;

import com.example.crudlearn.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    // Handle runtime exception - use FindById,..
    @ExceptionHandler({StudentNotFoundException.class,
                    CourseNotFoundException.class,
                    GradeNotFoundException.class,
                    StudentNotEnrolledException.class,
                    UserNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {

        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    // Handle delete value not in database
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(EmptyResultDataAccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList("Can not delete non-existing resource"));
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    // Handle when make post request with same subject - code Course has to be unique
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList("Data Integrity Violation: we can not process your request"));
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    // handle Validation @NotBlank @Score @Age @Past ...
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ObjectError error : ex.getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        ErrorResponse errorMessage = new ErrorResponse(errors);

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


}
