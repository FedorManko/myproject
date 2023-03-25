package com.example.myproject.service;

import com.example.myproject.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    List<PersonDto> getAllPersons();
    void savePerson(PersonDto personDTO);
    ResponseEntity<HttpStatus> changeFioById(String fio, Integer id);
}
