package com.example.myproject.service;

import com.example.myproject.dto.ClientDto;
import com.example.myproject.entity.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<ClientDto> getAllClients();
    ClientDto saveClient(ClientDto clientDTO);
    ClientDto changeFioById(String fio, UUID id);

    ResponseEntity<HttpStatus> deleteClientByFio(String fio);

   // Client loadClientByLogin(String login);
}
