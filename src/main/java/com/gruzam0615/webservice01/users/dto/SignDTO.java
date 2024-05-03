package com.gruzam0615.webservice01.users.dto;

import lombok.Data;

@Data
public class SignDTO {
    
    private String account;
    private String password;
    private String role;

}
