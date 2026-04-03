package com.example.Ecommerce.exception;

public class UnAuthorizedAccessException extends RuntimeException{
    public UnAuthorizedAccessException(String message){
        super(message);
    }
}
