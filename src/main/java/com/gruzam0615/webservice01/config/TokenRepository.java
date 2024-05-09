package com.gruzam0615.webservice01.config;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gruzam0615.webservice01.users.entity.Users;

public interface TokenRepository extends JpaRepository<Users, Long> {
    
    Optional<Users> findByUsersToken(String usersToken);

}
