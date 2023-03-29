package com.example.myproject.mapper;


import com.example.myproject.dto.UserAfterSuccessRegistrationForNotClientDto;
import com.example.myproject.dto.UserRegistrationForNotClientDto;
import com.example.myproject.entity.Client;
import com.example.myproject.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", uses = {UuidMapper.class}, injectionStrategy = CONSTRUCTOR)
public interface UserProfileMapper {


    @Mapping(source = "client.id", target = "idCustomer")
    @Mapping(source = "userProfile.smsNotification", target = "smsNotification")
    @Mapping(source = "client.mobilePhone", target = "mobilePhone")
    @Mapping(source = "userProfile.password", target = "passwordEncoded")
    @Mapping(source = "userProfile.securityQuestion", target = "securityQuestion")
    @Mapping(source = "userProfile.securityAnswer", target = "securityAnswer")
    @Mapping(source = "client.clientStatus", target = "clientStatus")
    @Mapping(source = "userProfile.appRegistrationDate", target = "dateAppRegistration")
    UserAfterSuccessRegistrationForNotClientDto toDtoAfterRegistrationNotClient(UserProfile userProfile, Client client);

    @Mapping(target = "smsNotification", constant = "true")
    @Mapping(source = "client", target = "client")
    @Mapping(target = "role", constant = "USER")
    UserProfile toUserProfileFromRegistrationForNotClientDto(
            UserRegistrationForNotClientDto userRegistrationForNotClientDto,
            Client client);
}