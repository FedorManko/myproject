package com.example.myproject.service.impl;

import com.example.myproject.dto.ClientDto;
import com.example.myproject.entity.Client;
import com.example.myproject.mapper.ClientMapper;
import com.example.myproject.mapper.ClientMapperImpl;
import com.example.myproject.repository.ClientRepository;
import com.example.myproject.util.DtoCreator;
import com.example.myproject.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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
    }

    @Test
    void changeFioById() {
    }

    @Test
    void deleteClientByFio() {
    }

    @Test
    void loadClientByLogin() {
    }
}