package com.pragma.home_service.category.domain.exception;

public class EmptyFieldException extends RuntimeException{
    public EmptyFieldException(String message) {
        super(message);
    }
}
