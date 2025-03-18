package com.pragma.home_service.category.infrastructure.exceptionhandler;

import com.pragma.home_service.category.domain.exception.DescriptionLengthExceededException;
import com.pragma.home_service.category.domain.exception.DuplicatedEntryException;
import com.pragma.home_service.category.domain.exception.EmptyFieldException;
import com.pragma.home_service.category.domain.exception.NameLengthExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(DescriptionLengthExceededException.class)
    public ResponseEntity<ExceptionResponse> handleDescriptionLengthExceededException(DescriptionLengthExceededException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(NameLengthExceededException.class)
    public ResponseEntity<ExceptionResponse> handleNameLengthExceededException(NameLengthExceededException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(DuplicatedEntryException.class)
    public ResponseEntity<ExceptionResponse> handleDescriptionLengthExceededException(DuplicatedEntryException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return ResponseEntity.status(409).body(response);
    }
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return ResponseEntity.badRequest().body(response);
    }
}
