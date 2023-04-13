package com.example.myproject.service.impl;


import com.example.myproject.dto.JwtTokenResponseDto;
import com.example.myproject.dto.UserAuthRequestDto;
import com.example.myproject.entity.Client;
import com.example.myproject.service.AuthenticationService;
import com.example.myproject.service.ClientService;
import com.example.myproject.service.exception.ErrorMessage;
import com.example.myproject.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final ClientService clientService;

    @Override
    @Transactional(readOnly = true)
    public JwtTokenResponseDto authenticate(UserAuthRequestDto authRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getLogin(), authRequestDto.getPassword()));

        final Client client = clientService.loadClientByLogin(authRequestDto.getLogin());
        final String jwtToken = jwtTokenUtil.generateAccessToken(client);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(client);

        return new JwtTokenResponseDto(jwtToken, refreshToken);
    }

    @Override
    @Transactional(readOnly = true)
    public JwtTokenResponseDto refreshAccessToken(String refreshToken) {
        if (jwtTokenUtil.validateRefreshToken(refreshToken)) {
            final Client client = clientService.loadClientByLogin(
                    jwtTokenUtil.getLoginFromRefreshToken(refreshToken));
            final String newAccessToken = jwtTokenUtil.generateAccessToken(client);
            final String newRefreshToken = jwtTokenUtil.generateRefreshToken(client);
            return new JwtTokenResponseDto(newAccessToken, newRefreshToken);
        }
        throw new JwtException(ErrorMessage.TOKEN_INVALID_MESS);
    }
}