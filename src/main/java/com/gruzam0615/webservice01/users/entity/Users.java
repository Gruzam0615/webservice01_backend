package com.gruzam0615.webservice01.users.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersIndex;
    private String usersName;
    private String usersPass;
    @Enumerated(value = EnumType.STRING)
    private UsersRole usersRole;
    private String usersToken;
    private String usersToken2;
    private LocalDateTime usersCreatedTime;
    private LocalDateTime usersUpdatedTime;

}
