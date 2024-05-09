package com.gruzam0615.webservice01.users.entity;

import java.util.List;
import java.util.LinkedList;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

public enum UsersRole {

    MEMBER("MEMBER"),
    ADMIN("ADMIN"),
    GUEST("GUEST");

    private String value;

    UsersRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public List<SimpleGrantedAuthority> getRole() {
        List<SimpleGrantedAuthority> result = new LinkedList<>();
        result.add(new SimpleGrantedAuthority(this.name()));
        return result;
    }
}
