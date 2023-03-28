package com.example.myproject.dto;

import com.example.myproject.validation.annotation.Age;
import com.example.myproject.validation.annotation.Fio;
import com.example.myproject.validation.annotation.Professions;
import lombok.Value;

import javax.validation.constraints.Size;

@Value
public class PersonDto {
    @Fio
   // @Size(min = 2, max = 30, message = "Full name length should be between 2 and 30 characters ")
    String fio;
    @Age
    Integer age;
    @Professions
    String profession;
}
