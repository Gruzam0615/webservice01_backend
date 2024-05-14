package com.gruzam0615.webservice01.users.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/user")
@Slf4j
public class UsersController {

    @GetMapping("/test01")
    public String test01Controller() {
        log.info("Called test01()"); 
        return new String(LocalDateTime.now().toString());
    }

    @GetMapping("/test02")
    public String test02Controller() {
        log.info("Called test02()");
        return new String(LocalDateTime.now().toString());
    }

}
