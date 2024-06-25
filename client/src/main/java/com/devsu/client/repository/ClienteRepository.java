package com.devsu.client.repository;

import com.devsu.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByIdentification(String identification);
}
