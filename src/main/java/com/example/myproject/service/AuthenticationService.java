package com.example.myproject.service;

import com.example.myproject.dto.JwtTokenResponseDto;
import com.example.myproject.dto.UserAuthRequestDto;

public interface AuthenticationService {

    JwtTokenResponseDto authenticate(UserAuthRequestDto authRequestDto);

    JwtTokenResponseDto refreshAccessToken(String refreshToken);
}
