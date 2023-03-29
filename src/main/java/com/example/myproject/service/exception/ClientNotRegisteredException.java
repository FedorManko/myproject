package com.example.myproject.service.exception;

import org.springframework.security.core.AuthenticationException;

public class ClientNotRegisteredException extends AuthenticationException {

    public ClientNotRegisteredException(String message) {
        super(message);
    }
}
