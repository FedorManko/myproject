package com.example.myproject.repository;


import com.example.myproject.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findById(UUID id);
    void deleteByFio(String fio);
    Optional<String> findByFio(String fio);
}
