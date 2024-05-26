package com.gruzam0615.webservice01.util.handler;

import java.util.NoSuchElementException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.TokenExpiredException;

import lombok.extern.slf4j.Slf4j;


/**
 * @ExceptionHandler 는 DispatcherServlet에서 ExceptionResolver 를 통해 Exception 을 처리할 때 작동된다.
 * @ExceptionHandler 가 작동하지 않는다면 해당 어노테이션이 작동하기 전에 SecurityFilter 에서 Exception 을 throws 한 것이다.
 * 
 * 이를 해결하려면 AuthenticationEntryPoint, AccessDeniedHandler 에 HandlerExceptionResolver 를 연결해
 * ExceptionResolver 가 해당 인증, 인가 예외를 처리할 수 있게 설정한다.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
       
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseHandler<Object>> globalException(Exception ex) {
        log.error("Global Exception");
        return ResponseEntity.ok()
            .body(ResponseHandler.<Object>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .data(null)
                .message(ex.getMessage())
                .build()
            );
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ResponseHandler<Object>> notFoundException(Exception ex) {
        log.error("NotFound Exception");
        return ResponseEntity.ok()
            .body(ResponseHandler.<Object>builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .data(null)
                .message(ex.getMessage())
                .build()
            );
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ResponseHandler<Object>> nullPointerException(NullPointerException ex) {
        log.error("NullPointer Exception");
        return ResponseEntity.ok()
            .body(ResponseHandler.<Object>builder()
                .httpStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value())
                .data(null)
                .message(ex.getMessage())
                .build()
            );
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ResponseHandler<Object>> noSuchElementException(NoSuchElementException ex) {
        log.error("NoSuchElement Exception");
        return ResponseEntity.ok()
            .body(ResponseHandler.<Object>builder()
                .httpStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value())
                .data(null)
                .message(ex.getMessage())
                .build()
            );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseHandler<Object>> authenticationException(AuthenticationException ex) {
        log.error("Authentication Exception");
        return ResponseEntity.ok()
            .body(ResponseHandler.<Object>builder()
                .httpStatus(HttpStatus.FORBIDDEN.value())
                .data(null)
                // .message(ex.getMessage())
                .message("Invalid Auth")
                .build()
            );
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ResponseHandler<Object>> tokenExpiredException(TokenExpiredException ex) {
        log.error("TokenExpired Exception");
        return ResponseEntity.ok()
            .body(ResponseHandler.<Object>builder()
                .httpStatus(HttpStatus.FORBIDDEN.value())
                .data(null)
                .message(ex.getMessage())
                .build()
            );
    }

}