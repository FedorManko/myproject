package com.example.myproject.mapper;


import com.example.myproject.dto.ClientDto;
import com.example.myproject.dto.UserInformationDto;
import com.example.myproject.dto.UserRegistrationForNotClientDto;
import com.example.myproject.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toDto(Client client);
    List<ClientDto> toDtoList(List<Client> clients);
    Client toClient(ClientDto clientDto);

    @Mapping(target = "clientStatus", constant = "ACTIVE")
    Client mapTo(UserRegistrationForNotClientDto userRegistrationForNotClientDto);
}
