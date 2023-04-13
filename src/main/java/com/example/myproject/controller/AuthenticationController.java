package com.example.myproject.controller;


import com.example.myproject.dto.ErrorExtension;
import com.example.myproject.dto.JwtTokenResponseDto;
import com.example.myproject.dto.TokenRefreshRequest;
import com.example.myproject.dto.UserAuthRequestDto;
import com.example.myproject.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication controller", description = "API for authentication clients")
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login and receive JWT tokens", description = "Receive pair of JWT tokens after login via mobile phone or passport number and password")
    @ApiResponse(responseCode = "200", description = "Successful authentication", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JwtTokenResponseDto.class))
    })
    @ApiResponse(responseCode = "401", description = "Invalid login or password", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorExtension.class))
    })

    public JwtTokenResponseDto oneTimeAuthorization(@Valid @RequestBody UserAuthRequestDto userAuthRequest) {
        return authService.authenticate(userAuthRequest);
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "authBearer")
    @Operation(summary = "Refresh JWT tokens with refresh token", description = "Receive new pair of JWT tokens by previous refresh token")
    @Parameters({
            @Parameter(in = ParameterIn.HEADER, name = "isRefreshToken", required = true,
                    schema = @Schema(type = "boolean", allowableValues = {"true", "false"}, defaultValue = "true"))
    })
    @ApiResponse(responseCode = "200", description = "Receive new pair of tokens", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JwtTokenResponseDto.class))
    })
    @ApiResponse(responseCode = "401", description = "Invalid or expired refresh token", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorExtension.class))
    })

    public JwtTokenResponseDto refreshJwtToken(@RequestBody TokenRefreshRequest refreshTokenDto) {
        return authService.refreshAccessToken(refreshTokenDto.getRefreshToken());
    }

}
