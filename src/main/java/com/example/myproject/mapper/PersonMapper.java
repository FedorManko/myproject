package com.example.myproject.mapper;


import com.example.myproject.dto.PersonDto;
import com.example.myproject.entity.Person;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto toDto(Person person );
    List<PersonDto> toDtoList(List<Person> persons);
    Person toPerson(PersonDto personDto);


}
