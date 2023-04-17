package com.example.myproject.service.impl;

import com.example.myproject.dto.UserAfterSuccessRegistrationForNotClientDto;
import com.example.myproject.dto.UserRegistrationForNotClientDto;
import com.example.myproject.entity.Client;
import com.example.myproject.entity.UserProfile;
import com.example.myproject.mapper.ClientMapper;
import com.example.myproject.mapper.UserProfileMapper;
import com.example.myproject.repository.ClientRepository;
import com.example.myproject.repository.UserProfileRepository;
import com.example.myproject.service.exception.ClientAlreadyRegisteredException;
import com.example.myproject.util.DtoCreator;
import com.example.myproject.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("Test class for RegistrationServiceImpl")
@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {
    @Mock
    private  ClientMapper clientMapper;
    @Mock
    private  UserProfileMapper userProfileMapper;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private  ClientRepository clientRepository;

    @InjectMocks
    private  RegistrationServiceImpl registrationService;

    @Test
    void checkExistsByPhonePublic() {
        Client client = EntityCreator.getClient();
        registrationService.checkExistsByPhonePublic(client.getMobilePhone());
        verify(clientRepository).findByLogin(client.getMobilePhone());
    }

    @Test
    void checkExistsByPhonePublicThrowClientAlreadyRegisteredException() {
        Client client = EntityCreator.getClient();
        when(clientRepository.findByLogin(client.getMobilePhone())).thenThrow(ClientAlreadyRegisteredException.class);
        assertThrows(ClientAlreadyRegisteredException.class,()->registrationService.checkExistsByPhonePublic(client.getMobilePhone()));
    }
    @Test
    void passwordEncoderPublic(){
        String password = "password123";
        UserProfile userProfile = new UserProfile();

        when(passwordEncoder.encode(password)).thenReturn("encodedPassword123");
        registrationService.passwordEncoderPublic(password, userProfile);

        verify(passwordEncoder).encode(password);
        assertEquals("encodedPassword123", userProfile.getPassword());
    }

    @Test
    void notClientMapperPublic(){
        Client client = EntityCreator.getClient();
        UserProfile userProfile = EntityCreator.getUserProfile();
        UserRegistrationForNotClientDto userRegistrationForNotClientDto = DtoCreator.getUserRegistrationForNotClientDto(userProfile);
        when(clientMapper.mapTo(userRegistrationForNotClientDto)).thenReturn(client);
        when(userProfileMapper.toUserProfileFromRegistrationForNotClientDto(userRegistrationForNotClientDto,client)).thenReturn(userProfile);
        UserProfile result = registrationService.notClientMapperPublic(userRegistrationForNotClientDto);
        verify(clientMapper).mapTo(userRegistrationForNotClientDto);
        verify(userProfileMapper).toUserProfileFromRegistrationForNotClientDto(userRegistrationForNotClientDto, client);
        verify(passwordEncoder).encode(userRegistrationForNotClientDto.getPassword());
        assertEquals(userProfile, result);
    }
//    @Test
//    void registrationForNotClient(){
//
//
//        UserProfile userProfile = EntityCreator.getUserProfile();
//        Client client = userProfile.getClient();
//        UserRegistrationForNotClientDto dto = DtoCreator.getUserRegistrationForNotClientDto(userProfile);
//        UserAfterSuccessRegistrationForNotClientDto expectedDto = DtoCreator.getUserAfterSuccessRegistrationForNotClientDto(userProfile);
//
//        registrationService.checkExistsByPhonePublic(client.getMobilePhone());
//        when(clientRepository.findByLogin(dto.getMobilePhone())).thenReturn(Optional.empty());
//        when(userProfileMapper.toDtoAfterRegistrationNotClient(userProfile, client)).thenReturn(expectedDto);
//        when(userProfileMapper.toUserProfileFromRegistrationForNotClientDto(dto, client)).thenReturn(userProfile);
//
//        // act
//        UserAfterSuccessRegistrationForNotClientDto result = registrationService.registrationForNotClient(dto);
//
//        // assert
//        verify(clientRepository).findByLogin(dto.getMobilePhone());
//        verify(clientRepository).save(userProfile.getClient());
//        verify(userProfileMapper).toDtoAfterRegistrationNotClient(userProfile, client);
//        verify(userProfileMapper).toUserProfileFromRegistrationForNotClientDto(dto, client);
//        assertEquals(expectedDto, result);
//    }

    @Test
    public void testRegistrationForNotClientThrowsClientAlreadyRegisteredException() {
        // arrange
        UserProfile userProfile = EntityCreator.getUserProfile();
        Client client = userProfile.getClient();
        UserRegistrationForNotClientDto dto = DtoCreator.getUserRegistrationForNotClientDto(userProfile);
        when(clientRepository.findByLogin(dto.getMobilePhone())).thenReturn(Optional.ofNullable(client));

        // act & assert
        assertThrows(ClientAlreadyRegisteredException.class, () -> registrationService.registrationForNotClient(dto));

        // verify
        verify(clientRepository).findByLogin(dto.getMobilePhone());
        verify(clientRepository, never()).save(any());
        verify(userProfileMapper, never()).toDtoAfterRegistrationNotClient(any(), any());
        verify(userProfileMapper, never()).toUserProfileFromRegistrationForNotClientDto(any(), any());
    }
}