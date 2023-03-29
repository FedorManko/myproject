package com.example.myproject.controller;


import com.example.myproject.dto.ClientDto;
import com.example.myproject.service.ClientService;
import com.example.myproject.validation.annotation.Fio;
import com.example.myproject.validation.annotation.Uuid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Validated
@Tag(name = "Client Controller", description = "Api to work with clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request for all clients", description =
            "Endpoint returns all branches.")
    @ApiResponse(responseCode = "200", description = "Clients list was successfully returned",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))))
    public List<ClientDto> getAllClients(){
        return clientService.getAllClients();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Save new client", description =
            "Endpoint saves new client.")
    @ApiResponse(responseCode = "200", description = "Client was successfully saved ",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))))
    public ClientDto saveClient(@Valid @RequestBody ClientDto clientDTO){
        return clientService.saveClient(clientDTO);
    }

    @PatchMapping("/change/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Change name of client by id", description =
            "Endpoint changes client name by id.")
    @ApiResponse(responseCode = "200", description = "Name of the client was successfully change",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))))
    public ClientDto changeFioById(@Fio @RequestParam(value = "fio") String fio,
                                   @Uuid @PathVariable("id") UUID id){
       return clientService.changeFioById(fio,id);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete client by fio", description =
            "Endpoint delete client by fio.")
    @ApiResponse(responseCode = "200", description = "Client was successfully delete",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))))
    public ResponseEntity<HttpStatus> deleteClientByFio(@Fio @RequestParam(value = "fio") String fio){
        return clientService.deleteClientByFio(fio);

    }
}
