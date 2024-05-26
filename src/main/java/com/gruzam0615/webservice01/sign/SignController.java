package com.gruzam0615.webservice01.sign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gruzam0615.webservice01.util.handler.ResponseHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/sign")
@RequiredArgsConstructor
@Slf4j
public class SignController {
    
    private final SignService signService;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseHandler<Boolean>> signUpController(@RequestBody SignDTO param) {
        boolean data = signService.signUpService(param);
        if(data == true) {
            return ResponseEntity.ok()
                .body(ResponseHandler.<Boolean>builder()
                    .httpStatus(HttpStatus.OK.value())
                    .message("signUp Complete")
                    .data(true)
                    .build()
                );
        } else {
            return ResponseEntity.ok()
                .body(ResponseHandler.<Boolean>builder()
                    .httpStatus(HttpStatus.OK.value())
                    .message("signUp InComplete")
                    .data(false)
                    .build()
                );
        }
    }

    @PostMapping(value = "/signIn", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseHandler<String>> signInController(@RequestBody SignDTO param) {
        log.info("Called signInController() Timestamp: {}", new Date(System.currentTimeMillis()).toString());
        log.info("username: {}", param.getAccount());

        String data = signService.signInService(param);
        if(data != null) {
            return ResponseEntity.ok()
                .body(ResponseHandler.<String>builder()
                    .httpStatus(HttpStatus.OK.value())
                    .message("signIn Complete")
                    .data(data)
                    .build()
                );
        } else {
            return ResponseEntity.ok()
                .body(ResponseHandler.<String>builder()
                    .httpStatus(HttpStatus.OK.value())
                    .message("signIn InComplete")
                    .data(data)
                    .build()
                );
        }
    }
    
    

}
