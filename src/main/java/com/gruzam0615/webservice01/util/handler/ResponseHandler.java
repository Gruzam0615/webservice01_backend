package com.gruzam0615.webservice01.util.handler;

import org.springframework.http.HttpStatus;

import lombok.Builder;

@Builder
public record ResponseHandler<T>(HttpStatus httpStatus, String message, T data) {
}
