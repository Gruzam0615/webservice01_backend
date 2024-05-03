package com.gruzam0615.webservice01.utils.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "NullPointerException", false);
        return handleExceptionInternal(exception, error, null, HttpStatus.BAD_REQUEST, null);
    }
    
}
