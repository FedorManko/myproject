package com.example.myproject.service.exception;

import lombok.Value;

@Value
public class ErrorExtension {

    String message;
    String errorCode;
}