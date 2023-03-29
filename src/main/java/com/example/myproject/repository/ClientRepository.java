package com.example.myproject.repository;


import com.example.myproject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findById(UUID id);
    void deleteByFio(String fio);
    Optional<String> findByFio(String fio);

    /**
     * Searching for Client by login (Phone number)
     *
     * @param login {@link String} ( Phone number)
     * @return Optional<Client>
     */
    @Query("from Client c where c.mobilePhone = :login")
   Optional<Client> findByLogin(@Param("login") String login);
}
