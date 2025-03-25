package com.pragma.home_service.category.domain.exception;

public class DuplicatedEntryException extends RuntimeException{
    public DuplicatedEntryException(String message) {
        super(message);
    }
}
