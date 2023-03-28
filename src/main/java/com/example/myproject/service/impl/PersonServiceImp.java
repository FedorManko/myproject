package com.example.myproject.service.impl;


import com.example.myproject.dto.PersonDto;
import com.example.myproject.entity.Person;
import com.example.myproject.mapper.PersonMapper;
import com.example.myproject.repository.PersonRepository;
import com.example.myproject.service.PersonService;
import com.example.myproject.service.exception.ErrorMessage;
import com.example.myproject.service.exception.PersonNotFoundException;
import com.example.myproject.service.exception.PersonWithSuchNameDontExistsException;
import com.example.myproject.service.exception.PersonWithSuchNameExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public List<PersonDto> getAllPersons() {
        log.info("Get a dto list of people");
        return personMapper.toDtoList(personRepository.findAll());
    }

    @Override
    @Transactional
    public PersonDto savePerson(PersonDto personDTO) {
        if(personRepository.findByFio(personDTO.getFio()).isPresent()){
            throw new PersonWithSuchNameExistsException(ErrorMessage.PERSON_WITH_SUCH_NAME_EXISTS);
        }
        personRepository.save(personMapper.toPerson(personDTO));
        return personDTO;
    }

    @Override
    @Transactional
    public PersonDto changeFioById(String fio, UUID id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(ErrorMessage.PERSON_NOT_EXISTS));
        person.setFio(fio);
        personRepository.save(person);
        return personMapper.toDto(person);
    }
    @Override
    @Transactional
    public ResponseEntity<HttpStatus> deletePersonByFio(String fio) {
        if(personRepository.findByFio(fio).isPresent()) {
            personRepository.deleteByFio(fio);
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            throw new PersonWithSuchNameDontExistsException(ErrorMessage.PERSON_WITH_SUCH_NAME_DONT_EXISTS);
        }

    }
}
