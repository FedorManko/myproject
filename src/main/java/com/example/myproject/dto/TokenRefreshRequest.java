package com.example.myproject.dto;

import com.example.myproject.validation.annotation.JwtToken;
import lombok.Value;

@Value
public class TokenRefreshRequest {

    @JwtToken
    String refreshToken;
}
