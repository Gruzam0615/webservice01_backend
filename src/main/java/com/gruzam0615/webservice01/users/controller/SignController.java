package com.gruzam0615.webservice01.users.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gruzam0615.webservice01.users.dto.SignDTO;
import com.gruzam0615.webservice01.users.entity.Users;
import com.gruzam0615.webservice01.users.entity.UsersRole;
import com.gruzam0615.webservice01.users.service.UsersService;
import com.gruzam0615.webservice01.utils.handler.ResponseHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/sign")
@RequiredArgsConstructor
@Slf4j
public class SignController {
    
    private final UsersService usersService;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseHandler<Boolean>> signUpMethod(@RequestBody SignDTO param) {        
        Users entity = new Users();
        entity.setUsersName(param.getAccount());
        entity.setUsersPass(param.getPassword());

        if(param.getRole() == null) {
            return ResponseEntity
                .ok()
                .body(ResponseHandler.<Boolean>builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("signUp InComplete")
                    .data(false)
                .build()
            );
        } else {
            entity.setUsersRole(UsersRole.valueOf(param.getRole()));
        }

        Users data = usersService.save(entity);
        if(data.equals(null)) {
            return ResponseEntity
                .ok()
                .body(ResponseHandler.<Boolean>builder()
                    .status(HttpStatus.CONFLICT)
                    .message("signUp InComplete")
                    .data(false)
                .build()
            );
        } else {
            return ResponseEntity
            .ok()
            .body(ResponseHandler.<Boolean>builder()
                .status(HttpStatus.OK)
                .message("signUp Complete")
                .data(true)
                .build()
            );
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<ResponseHandler<String>> signInMethod(@RequestBody SignDTO entity) {
        String result = usersService.signInProcess(entity);

        if(result != null) {
            return ResponseEntity
            .ok()
            .body(ResponseHandler.<String>builder()
                .status(HttpStatus.OK)
                .message("signIn Complete")
                .data(result)
                .build()
            );
        } else {
            return ResponseEntity
            .ok()
            .body(ResponseHandler.<String>builder()
                .status(HttpStatus.OK)
                .message("signIn InComplete")
                .data(null)
                .build()
            );
        }
        
    }
    
    


}
