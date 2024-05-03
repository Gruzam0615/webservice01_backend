package com.gruzam0615.webservice01.utils.handler;

import org.springframework.http.HttpStatus;

import lombok.Builder;

@Builder
public record ResponseHandler<T>(HttpStatus status, String message, T data) {
}
