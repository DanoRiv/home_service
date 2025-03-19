package com.pragma.home_service.category.infrastructure.exceptionhandler;

public class ExceptionConstants {
    private ExceptionConstants() { throw new IllegalStateException("Utility class");}

    public static final String NAME_LENGTH_EXCEEDED = "Name length cannot exceed 50 characters";
    public static final String DESCRIPTION_LENGTH_EXCEEDED = "Description length cannot exceed 90 characters";
}
