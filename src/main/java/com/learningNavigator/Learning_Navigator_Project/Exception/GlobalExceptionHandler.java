package com.learningNavigator.Learning_Navigator_Project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ResouceAlreadyExistsException.class)
    public ResponseEntity<?> resouceAlreadyExistsException(ResouceAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(IdNotOfLengthException.class)
    public ResponseEntity<?> idNotOfLengthException(IdNotOfLengthException ex){
        return ResponseEntity.status(HttpStatus.LENGTH_REQUIRED).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                                                "It can be one of the following errors:\n" + 
                                                "- Subject ID is not 5 letters long.\n" +
                                                "- Exam ID is not 5 letters long.\n" + 
                                                "- Student ID is not 5 letters long.\n" + 
                                                "- Mapping is not supported.\n" +
                                                "- Incorrect URL.");
    }
}
