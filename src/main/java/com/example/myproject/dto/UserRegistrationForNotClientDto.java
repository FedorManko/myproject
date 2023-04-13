package com.example.myproject.dto;


import com.example.myproject.validation.annotation.*;
import lombok.Value;

import javax.persistence.criteria.CriteriaBuilder;


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
    @Fio
    String fio;
    @Age
    Integer age;
    @Professions
    String profession;
}
