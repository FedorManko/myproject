package com.example.myproject.service.exception;

public class ErrorMessage {
    public static final String CLIENT_NOT_EXISTS = "Client with this id not exists";
    public static final String TEMPORARY_UNAVAILABLE = "Service temporary unavailable";
    public static final String CLIENT_WITH_SUCH_NAME_DONT_EXISTS = "Client with this name don't exists";
    public static final String CLIENT_WITH_SUCH_NAME_EXISTS = "Client with this name exists";

    public static final String TOKEN_INVALID_MESS = "JWT access token invalid or expired";
}
