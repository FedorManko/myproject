package com.example.myproject.dto;


import com.example.myproject.validation.annotation.Password;
import com.example.myproject.validation.annotation.PhoneNumber;
import com.example.myproject.validation.annotation.SecurityQuestionAnswer;
import lombok.Value;



@Value
public class UserRegistrationForNotClientDto {

    @PhoneNumber
    String mobilePhone;
    @Password
    String password;
    @SecurityQuestionAnswer
    String securityQuestion;
    @SecurityQuestionAnswer
    String securityAnswer;
}
