package com.example.myproject.exceptions;

public class MapperException extends RuntimeException{
    public MapperException() {
        super();
    }

    public MapperException(String message) {
        super(message);
    }
}
