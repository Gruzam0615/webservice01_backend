package com.gruzam0615.webservice01.sign;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class SignOutDTO {
    
    private HttpStatus httpStatus;
    private String message;
    private Object data;

}
