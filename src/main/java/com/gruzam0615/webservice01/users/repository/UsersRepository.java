package com.gruzam0615.webservice01.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gruzam0615.webservice01.users.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    public Optional<Users> findByUsersName(String usersName);

}
