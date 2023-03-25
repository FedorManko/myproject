package com.example.myproject.controller;



import com.example.myproject.service.exception.ErrorCode;
import com.example.myproject.service.exception.ErrorExtension;
import com.example.myproject.service.exception.PersonNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleClientNotFoundException(Exception ex) {
        ErrorExtension body = new ErrorExtension(
                ex.getMessage(),
                ErrorCode.PERSON_NOT_FOUND);
        return new ResponseEntity<>(body, NOT_FOUND);
    }




}
