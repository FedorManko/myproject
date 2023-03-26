package com.example.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy ="com.example.myproject.generator.UuidTimeSequenceGenerator" )
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "age")
    private Integer age;

    @Column(name = "profession")
    @Enumerated(value = EnumType.STRING)
    private Profession profession;

}
