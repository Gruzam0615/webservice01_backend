package com.gruzam0615.webservice01.token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gruzam0615.webservice01.users.entity.Users;

public interface TokenRepository extends JpaRepository<Token, Long> {
    
    // Optional<Users> findByUsersToken(String usersToken);

    Optional<Token> findByToken(String token);

}
