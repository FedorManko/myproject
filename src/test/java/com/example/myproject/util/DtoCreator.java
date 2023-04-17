package com.example.myproject.util;

import com.example.myproject.dto.ClientDto;
import com.example.myproject.dto.UserAfterSuccessRegistrationForNotClientDto;
import com.example.myproject.dto.UserRegistrationForNotClientDto;
import com.example.myproject.entity.Client;
import com.example.myproject.entity.UserProfile;

public class DtoCreator {

    public static ClientDto getClientDto(Client client) {
        return new ClientDto(
                client.getFio(),
                client.getAge(),
                client.getProfession().name(),
                client.getMobilePhone(),
                client.getClientStatus());
    }

    public static UserRegistrationForNotClientDto getUserRegistrationForNotClientDto(UserProfile userProfile) {
        return new UserRegistrationForNotClientDto(
                userProfile.getClient().getMobilePhone(),
                userProfile.getPassword(),
                userProfile.getSecurityQuestion(),
                userProfile.getSecurityAnswer(),
                userProfile.getClient().getFio(),
                userProfile.getClient().getAge(),
                userProfile.getClient().getProfession().name());

    }

    public static UserAfterSuccessRegistrationForNotClientDto getUserAfterSuccessRegistrationForNotClientDto(UserProfile userProfile){
        return new UserAfterSuccessRegistrationForNotClientDto(
                userProfile.getId().toString(),
                userProfile.getSmsNotification().toString(),
                userProfile.getClient().getMobilePhone(),
                userProfile.getPassword(),
                userProfile.getSecurityQuestion(),
                userProfile.getSecurityAnswer(),
                userProfile.getClient().getClientStatus(),
                userProfile.getRole(),
                userProfile.getAppRegistrationDate()
        );
    }
}
