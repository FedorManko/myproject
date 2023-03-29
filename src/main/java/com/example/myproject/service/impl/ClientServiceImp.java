package com.example.myproject.service.impl;


import com.example.myproject.dto.ClientDto;
import com.example.myproject.entity.Client;
import com.example.myproject.mapper.ClientMapper;
import com.example.myproject.repository.ClientRepository;
import com.example.myproject.service.ClientService;
import com.example.myproject.service.exception.ErrorMessage;
import com.example.myproject.service.exception.ClientNotFoundException;
import com.example.myproject.service.exception.ClientWithSuchNameDontExistsException;
import com.example.myproject.service.exception.ClientWithSuchNameExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getAllClients() {
        log.info("Get a dto list of client");
        return clientMapper.toDtoList(clientRepository.findAll());
    }

    @Override
    @Transactional
    public ClientDto saveClient(ClientDto clientDTO) {
        if(clientRepository.findByFio(clientDTO.getFio()).isPresent()){
            throw new ClientWithSuchNameExistsException(ErrorMessage.CLIENT_WITH_SUCH_NAME_EXISTS);
        }
        log.info("Save a new client");
        clientRepository.save(clientMapper.toClient(clientDTO));
        return clientDTO;
    }

    @Override
    @Transactional
    public ClientDto changeFioById(String fio, UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(ErrorMessage.CLIENT_NOT_EXISTS));
        client.setFio(fio);
        clientRepository.save(client);
        log.info("Change name of the client");
        return clientMapper.toDto(client);
    }
    @Override
    @Transactional
    public ResponseEntity<HttpStatus> deleteClientByFio(String fio) {
        if(clientRepository.findByFio(fio).isPresent()) {
            clientRepository.deleteByFio(fio);
            log.info("Delete exists client");
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            throw new ClientWithSuchNameDontExistsException(ErrorMessage.CLIENT_WITH_SUCH_NAME_DONT_EXISTS);
        }

    }

//    @Override
//    @Transactional(readOnly = true)
//    public Client loadClientByLogin(String login) throws UsernameNotFoundException {
//        return clientRepository.findByLogin(login)
//                .orElseThrow(() -> new ClientNotFoundException(String.format("Unknown user %s", login)));
//    }
}
