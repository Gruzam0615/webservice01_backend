package com.gruzam0615.webservice01.users.service;

import org.springframework.stereotype.Service;

import com.gruzam0615.webservice01.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsersService {
    
    private final UsersRepository usersRepository;

}
