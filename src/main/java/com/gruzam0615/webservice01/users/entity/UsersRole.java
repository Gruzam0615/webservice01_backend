package com.gruzam0615.webservice01.users.entity;

public enum UsersRole {
    
    ADMIN("ADMIN"),
    MEMBER("MEMBER"),
    GUEST("GUEST");

    private String value;

    UsersRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
