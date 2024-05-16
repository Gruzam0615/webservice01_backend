package com.gruzam0615.webservice01.users.entity;

import java.util.Collection;
import java.time.LocalDateTime;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gruzam0615.webservice01.token.Token;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersIndex;
    private String usersName;
    private String usersPass;
    @Enumerated(EnumType.STRING)
    private UsersRole usersRole;
    private LocalDateTime usersCreatedTime;
    private LocalDateTime usersUpdatedTime;

    @OneToOne(mappedBy = "user")
    private Token tokens;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usersRole.getRole();
    }

    @Override
    public String getPassword() {
        return usersPass;
    }

    @Override
    public String getUsername() {
        return usersName;
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
