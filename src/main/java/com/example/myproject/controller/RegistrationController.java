package com.example.myproject.controller;

import com.example.myproject.dto.ErrorExtension;
import com.example.myproject.dto.UserAfterSuccessRegistrationForNotClientDto;
import com.example.myproject.dto.UserRegistrationForNotClientDto;
import com.example.myproject.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@Tag(name = "Registration controller", description = "API for registration clients")
public class RegistrationController {

    private final RegistrationService registrationService;


    @PostMapping("/user-profile/new")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register non-existing client", description = "Save new client info and create user profile in database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New client has been successfully registered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserAfterSuccessRegistrationForNotClientDto.class))),
            @ApiResponse(responseCode = "409", description = "Can't create profile for client if phone number or passport number is already registered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorExtension.class)))
    })
    public UserAfterSuccessRegistrationForNotClientDto registrationForNotClient(
            @Valid @RequestBody UserRegistrationForNotClientDto userRegistrationForNotClientDto) {
        return registrationService.registrationForNotClient(userRegistrationForNotClientDto);
    }

}