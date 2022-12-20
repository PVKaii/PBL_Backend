package com.example.pbl_api.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppException {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception e){
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
