package com.example.libraryManagement.exception;

import com.example.libraryManagement.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBookNotFound(BookNotFoundException exception){
        Map<String,Object> error = new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("message", exception.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException exception){
        Map<String,Object> error = new HashMap<>();
        error.put("status",HttpStatus.BAD_REQUEST.value());
        Map<String,String> fieldErrors = new HashMap<>();
        for (FieldError fe : exception.getBindingResult().getFieldErrors()){
            fieldErrors.put(fe.getField(),fe.getDefaultMessage());
        }
        error.put("errors",fieldErrors);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String,Object>> handleBadCredentials(BadCredentialsException exception){
        Map<String, Object> error = new HashMap<>();
        error.put("status",HttpStatus.UNAUTHORIZED.value());
        error.put("message","Invalid username or password");
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(BookAlreadyIssuedException.class)
    public ResponseEntity<Map<String,Object>> handleBookAlreadyIssued(BookAlreadyIssuedException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("status",HttpStatus.CONFLICT.value());
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
}
