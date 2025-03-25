package com.pragma.home_service.category.infrastructure.exceptionhandler;

import com.pragma.home_service.category.domain.exception.DescriptionLengthExceededException;
import com.pragma.home_service.category.domain.exception.DuplicatedEntryException;
import com.pragma.home_service.category.domain.exception.EmptyFieldException;
import com.pragma.home_service.category.domain.exception.NameLengthExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(String.format(exception.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
    }
    @ExceptionHandler(DuplicatedEntryException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicatedEntryException(DuplicatedEntryException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT.toString(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return ResponseEntity.status(409).body(response);
    }
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(NameLengthExceededException.class)
    public ResponseEntity<ExceptionResponse> handleNameLengthExceededException(NameLengthExceededException exception){
        ExceptionResponse response = new ExceptionResponse(ExceptionConstants.NAME_LENGTH_EXCEEDED, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(DescriptionLengthExceededException.class)
    public ResponseEntity<ExceptionResponse> handleDescriptionLengthExceededException(DescriptionLengthExceededException exception){
        ExceptionResponse response = new ExceptionResponse(ExceptionConstants.DESCRIPTION_LENGTH_EXCEEDED, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return ResponseEntity.badRequest().body(response);
    }
}
