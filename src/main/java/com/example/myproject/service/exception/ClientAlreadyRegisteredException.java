package com.example.myproject.service.exception;

public class ClientAlreadyRegisteredException extends RegistrationException {

    public ClientAlreadyRegisteredException(String message) {
        super(message);
    }
}
