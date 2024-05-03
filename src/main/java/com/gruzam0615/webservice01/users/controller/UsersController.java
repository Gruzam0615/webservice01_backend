package com.gruzam0615.webservice01.users.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gruzam0615.webservice01.users.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UsersController {
    
    private final UsersService usersService;    

}
