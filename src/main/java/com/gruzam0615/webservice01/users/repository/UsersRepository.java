package com.gruzam0615.webservice01.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gruzam0615.webservice01.users.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Optional<Users> findByUsersName(String usersName);

}
