package com.example.myproject.service.impl;

import com.example.myproject.dto.ClientDto;
import com.example.myproject.entity.Client;
import com.example.myproject.mapper.ClientMapper;
import com.example.myproject.mapper.ClientMapperImpl;
import com.example.myproject.repository.ClientRepository;
import com.example.myproject.service.exception.ClientNotFoundException;
import com.example.myproject.service.exception.ClientWithSuchNameDontExistsException;
import com.example.myproject.util.DtoCreator;
import com.example.myproject.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@DisplayName("Test class for ClientServiceImpl")
@ExtendWith(MockitoExtension.class)
class ClientServiceImpTest {
    @Mock
    private ClientRepository clientRepository;
    @Spy
    private ClientMapper clientMapper = new ClientMapperImpl();
    @InjectMocks
    private ClientServiceImp clientServiceImp;


    @DisplayName("Testing for getAllClients()")
    @Test
    public void testGetAllClients() {
        List<Client> clients = List.of(EntityCreator.getClient());
        when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDto> actualList = clientServiceImp.getAllClients();
        List<ClientDto> expectedList = clients.stream()
                .map(DtoCreator::getClientDto)
                .collect(Collectors.toList());

        assertEquals(expectedList, actualList);
        verify(clientRepository).findAll();
    }

    @Test
    void saveClient() {
        Client client = EntityCreator.getClient();
        ClientDto clientDto = DtoCreator.getClientDto(client);
        when(clientRepository.findByFio(anyString())).thenReturn(Optional.empty());
        when(clientMapper.toClient(clientDto)).thenReturn(client);
        ClientDto savedClient = clientServiceImp.saveClient(clientDto);
        assertNotNull(savedClient);
        assertEquals(clientDto.getFio(), savedClient.getFio());
        // Проверяем, что методы были вызваны корректно
        verify(clientRepository, times(1)).findByFio(anyString());
        verify(clientRepository, times(1)).save(client);

    }

    @Test
    void saveClientThrowClientWithSuchNameExistsException() {
        Client client = EntityCreator.getClient();
        ClientDto clientDto = DtoCreator.getClientDto(client);
        when(clientRepository.findByFio(clientDto.getFio())).thenThrow(ClientWithSuchNameDontExistsException.class);
        assertThrows(ClientWithSuchNameDontExistsException.class,()->clientServiceImp.saveClient(clientDto));

    }

    @Test
    void changeFioById() {
        Client client = EntityCreator.getClient();
        ClientDto clientDto = DtoCreator.getClientDto(client);
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.toDto(client)).thenReturn(clientDto);

        // act
        ClientDto result = clientServiceImp.changeFioById(any(), client.getId());

        // assert
        verify(clientRepository, times(1)).findById(client.getId());
        verify(clientRepository, times(1)).save(client);
        verifyNoMoreInteractions(clientRepository);

        assertEquals(clientDto, result);
    }
    @Test
    void changeFioByIdClientNotFoundException() {
        Client client = EntityCreator.getClient();
        when(clientRepository.findById(client.getId())).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class,()->clientServiceImp.changeFioById(anyString(),client.getId()));
    }


    @Test
    void deleteClientByFio() {

        Client client = EntityCreator.getClient();
        ClientDto clientDto = DtoCreator.getClientDto(client);
        when(clientRepository.findByFio(client.getFio())).thenReturn(Optional.of(client.getFio()));
        ResponseEntity<HttpStatus> response = clientServiceImp.deleteClientByFio(client.getFio());
        verify(clientRepository, times(1)).deleteByFio(clientDto.getFio());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteClientByFioTrowClientWithSuchNameDontExistsException() {

        Client client = EntityCreator.getClient();
        when(clientRepository.findByFio(client.getFio())).thenReturn(Optional.empty());
        assertThrows(ClientWithSuchNameDontExistsException.class,()-> clientServiceImp.deleteClientByFio(client.getFio()));

    }

    @Test
    void loadClientByLogin() {
        String login = "test";
        Client client = EntityCreator.getClient();
        when(clientRepository.findByLogin(login)).thenReturn(Optional.of(client));
        Client expectedClient = clientServiceImp.loadClientByLogin(login);
        assertEquals(expectedClient,client);
    }

    @Test
    void loadClientByLoginThrowClientNotFoundException() {
        when(clientRepository.findByLogin(anyString())).thenThrow(ClientNotFoundException.class);
        assertThrows(ClientNotFoundException.class,()->clientServiceImp.loadClientByLogin(anyString()));
    }
}