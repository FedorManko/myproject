package com.example.myproject.dto;

import com.example.myproject.entity.ClientStatus;
import com.example.myproject.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserAfterSuccessRegistrationForNotClientDto {

    String idCustomer;
    String smsNotification;
    String mobilePhone;
    String passwordEncoded;
    String securityQuestion;
    String securityAnswer;
    ClientStatus clientStatus;
    UserRole role;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate dateAppRegistration;
}
