package com.example.OneToOne.exception;

import com.example.OneToOne.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ApiResponse<>(404,ex.getMessage(),null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound2(Exception ex){
        return new ResponseEntity<>(new ApiResponse<>(500,ex.getMessage(),null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
