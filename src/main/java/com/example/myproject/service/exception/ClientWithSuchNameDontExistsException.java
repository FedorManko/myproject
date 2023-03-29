package com.example.myproject.service.exception;

public class ClientWithSuchNameDontExistsException extends RuntimeException {
    public ClientWithSuchNameDontExistsException(String message) {
        super(message);
    }
}
