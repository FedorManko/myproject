package com.example.myproject.service.exception;

public class ClientWithSuchNameExistsException extends RuntimeException {
    public ClientWithSuchNameExistsException(String message) {
        super(message);
    }
}
