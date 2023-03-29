package com.example.myproject.dto;

import com.example.myproject.validation.annotation.Login;
import com.example.myproject.validation.annotation.Password;
import lombok.Value;

@Value
public class UserAuthRequestDto {

    @Login
    String login;

    @Password
    String password;
}
