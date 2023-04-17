package com.example.myproject.service.impl;


import com.example.myproject.dto.UserAfterSuccessRegistrationForNotClientDto;
import com.example.myproject.dto.UserRegistrationForNotClientDto;
import com.example.myproject.entity.Client;
import com.example.myproject.entity.UserProfile;
import com.example.myproject.mapper.ClientMapper;
import com.example.myproject.mapper.UserProfileMapper;
import com.example.myproject.repository.ClientRepository;
import com.example.myproject.repository.UserProfileRepository;
import com.example.myproject.service.RegistrationService;
import com.example.myproject.service.exception.ClientAlreadyRegisteredException;
import com.example.myproject.service.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final ClientMapper clientMapper;
    private final UserProfileMapper userProfileMapper;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    @Override
    @Transactional
    public UserAfterSuccessRegistrationForNotClientDto registrationForNotClient(UserRegistrationForNotClientDto dto) {
        checkExistsByPhone(dto.getMobilePhone());
        UserProfile userProfile = userProfileRepository.save(notClientMapper(dto));
        return userProfileMapper.toDtoAfterRegistrationNotClient(userProfile,
                userProfile.getClient());
    }

    public void checkExistsByPhonePublic(String phone) {
        checkExistsByPhone(phone);
    }

    public void passwordEncoderPublic(String password, UserProfile userProfile){
        passwordEncoder(password,userProfile);
    }

    private void checkExistsByPhone(String phone) {
        Optional.ofNullable(clientRepository.findByLogin(phone))
                .filter(Optional::isEmpty)
                .orElseThrow(() -> new ClientAlreadyRegisteredException(ErrorMessage.CLIENT_WITH_THIS_PHONE_EXISTS));
    }
    private void passwordEncoder(String password, UserProfile userProfile) {
        userProfile.setPassword(passwordEncoder.encode(password));
    }
    public UserProfile notClientMapperPublic(UserRegistrationForNotClientDto userRegistrationForNotClientDto){
        return notClientMapper(userRegistrationForNotClientDto);
    }

    private UserProfile notClientMapper(UserRegistrationForNotClientDto userRegistrationForNotClientDto) {
        Client client = clientMapper.mapTo(userRegistrationForNotClientDto);
        UserProfile userProfile = userProfileMapper.toUserProfileFromRegistrationForNotClientDto(
                userRegistrationForNotClientDto, client);
        passwordEncoder(userRegistrationForNotClientDto.getPassword(), userProfile);
        return userProfile;
    }


}