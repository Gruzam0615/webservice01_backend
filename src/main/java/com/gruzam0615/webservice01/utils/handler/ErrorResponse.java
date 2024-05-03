package com.gruzam0615.webservice01.utils.handler;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {
    
    private final HttpStatus status;
    private final String message;
    private final Object data;

}
