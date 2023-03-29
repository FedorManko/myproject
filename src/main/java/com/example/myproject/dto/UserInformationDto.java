package com.example.myproject.dto;

import lombok.Value;

@Value
public class UserInformationDto {

    String firstName;

    String lastName;

    String middleName;

    String mobilePhone;

    String email;

    String passportNumber;

    String securityQuestion;

    String clientStatus;

    String qrGoogleSecret;

    String qrGoogleHex;

}