package com.example.myproject.controller;


import com.example.myproject.dto.PersonDto;
import com.example.myproject.service.PersonService;
import com.example.myproject.validation.annotation.Fio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
@Validated
@Tag(name = "Person Controller")
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request for all persons", description =
            "Endpoint returns all branches.")
    @ApiResponse(responseCode = "200", description = "Persons list was successfully returned",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))))
    public List<PersonDto> getAllPersons(){
        return personService.getAllPersons();
    }

    @PostMapping()
    @Operation(summary = "Save new person", description =
            "Endpoint saves new person.")
    @ApiResponse(responseCode = "200", description = "Person was successfully saved ",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))))
    public void savePerson(@RequestBody PersonDto personDTO){
        personService.savePerson(personDTO);
    }

    @PatchMapping("/change/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Change name of person by id", description =
            "Endpoint changes person name by id.")
    @ApiResponse(responseCode = "200", description = "Name of the person was successfully change",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))))
    public void changeFioById(@Fio @RequestParam(value = "fio") String fio,
                              @PathVariable("id") UUID id){
        personService.changeFioById(fio,id);
    }
}
