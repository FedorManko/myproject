package com.example.myproject.dto;

import lombok.Value;

@Value
public class JwtTokenResponseDto {

    String jwtToken;
    String refreshToken;
}
