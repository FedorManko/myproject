package com.example.myproject.util;

import com.example.myproject.dto.ClientDto;
import com.example.myproject.entity.Client;

public class DtoCreator {

    public static ClientDto getClientDto(Client client) {
        return new ClientDto(
                client.getFio(),
                client.getAge(),
                client.getProfession().name(),
                client.getMobilePhone(),
                client.getClientStatus());
    }
}
