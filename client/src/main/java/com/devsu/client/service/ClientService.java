package com.devsu.client.service;

import com.devsu.client.model.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    /**
     * Save new client
     *
     * @param clientDto client to save
     */
    void saveClient(ClientDto clientDto);

    /**
     * Save a client list
     *
     * @param clientDtos clients to save
     */
    void saveClientList(List<ClientDto> clientDtos);

    /**
     * Update client by id
     *
     * @param id client identifier
     * @param clientDto object to update
     */
    void updateClient(Long id, ClientDto clientDto);

    /**
     * Delete a client
     * @param id client identifier
     */
    void deleteClient(Long id);

    /**
     * Retrieve a client by id if exists, otherwise return an empty optional
     *
     * @param id client identifier
     * @return optional object
     */
    Optional<ClientDto> findClientById(Long id);

    /**
     * Return all clients
     *
     * @return clients list
     */
    List<ClientDto> findAllClients();
}
