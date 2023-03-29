package com.example.myproject.service;


import com.example.myproject.dto.UserAfterSuccessRegistrationForNotClientDto;
import com.example.myproject.dto.UserRegistrationForNotClientDto;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {

    UserAfterSuccessRegistrationForNotClientDto registrationForNotClient(UserRegistrationForNotClientDto dto);


}