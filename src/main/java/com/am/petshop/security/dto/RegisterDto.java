package com.am.petshop.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class RegisterDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}
