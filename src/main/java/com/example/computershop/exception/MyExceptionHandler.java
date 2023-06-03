package com.example.computershop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerException (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Error: %s", e.getMessage()));
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handlerException (ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Error: %s", e.getMessage()));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handlerException (HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Error: %s", e.getMessage()));
    }
}