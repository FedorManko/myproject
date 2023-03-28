package com.example.myproject.exceptions.handlers;

import com.example.myproject.dto.ErrorExtension;
import com.example.myproject.dto.ErrorResponse;
import com.example.myproject.service.exception.*;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.net.ConnectException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ErrorExtension> extensions = exception.getConstraintViolations()
                .stream()
                .map(violation -> new ErrorExtension(violation.getMessage(), ErrorCode.INVALID_PATH_VARIABLE))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.VALIDATION_FAILED, extensions), BAD_REQUEST);
    }

    @Override
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ErrorExtension> errorExtensions = exception.getFieldErrors()
                .stream()
                .map(filedError -> new ErrorExtension(filedError.getDefaultMessage(),
                        String.format("invalid_%s", filedError.getField())))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.VALIDATION_FAILED, errorExtensions), BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    @ApiResponse(responseCode = "503", description = "Temporary unavailable",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorExtension.class)))
    public ResponseEntity<ErrorExtension> handleConnectException() {
        ErrorExtension body = new ErrorExtension(
                ErrorMessage.TEMPORARY_UNAVAILABLE,
                SERVICE_UNAVAILABLE.name().toLowerCase());
        return new ResponseEntity<>(body, SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorExtension> handlePersonNotFoundException(Exception ex) {
        ErrorExtension body = new ErrorExtension(
                ex.getMessage(),
                ErrorCode.PERSON_NOT_FOUND);
        return new ResponseEntity<>(body, NOT_FOUND);
    }
    @ExceptionHandler(PersonWithSuchNameDontExistsException.class)
    public ResponseEntity<ErrorExtension> handlePersonWithSuchNameDontExistsException(Exception ex) {
        ErrorExtension body = new ErrorExtension(
                ex.getMessage(),
                ErrorCode.PERSON_WITH_SUCH_NAME_DONT_EXISTS);
        return new ResponseEntity<>(body, NOT_FOUND);
    }
    @ExceptionHandler(PersonWithSuchNameExistsException.class)
    public ResponseEntity<ErrorExtension> handlePersonWithSuchNameExistsException(Exception ex) {
        ErrorExtension body = new ErrorExtension(
                ex.getMessage(),
                ErrorCode.PERSON_WITH_SUCH_NAME_EXISTS);
        return new ResponseEntity<>(body, NOT_FOUND);
    }
}