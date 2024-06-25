package com.devsu.client.service.impl;

import com.devsu.client.exception.NotFoundException;
import com.devsu.client.mapper.ClientMapper;
import com.devsu.client.model.Client;
import com.devsu.client.model.dto.ClientDto;
import com.devsu.client.repository.ClienteRepository;
import com.devsu.client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Client with all client operations
 */
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Client with ID %s not found";
    private final ClienteRepository repository;
    private final ClientMapper mapper;

    public ClientServiceImpl(ClienteRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveClient(ClientDto clientDto) {
        log.info("Adding new client with identification number: {}", clientDto.getIdentification());
        repository.save(mapper.toEntity(clientDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveClientList(List<ClientDto> clientDtos) {
        List<Client> clients = clientDtos.stream().map(mapper::toEntity).toList();

        repository.saveAll(clients);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClient(Long id, ClientDto clientDto) {
        log.info("Updating client with ID: {}", id);
        findClientById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MESSAGE, id)));
        clientDto.setId(id);
        repository.save(mapper.toEntity(clientDto));
    }

    /**
     * {@inheritDoc}
     */
    public void deleteClient(Long id) {
        log.info("Deleting client with ID: {}", id);
        findClientById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MESSAGE, id)));
        repository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    public Optional<ClientDto> findClientById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ClientDto> findClientByIdentification(String identification) {
        return repository.findByIdentification(identification).map(mapper::toDto);
    }

    /**
     * {@inheritDoc}
     */
    public List<ClientDto> findAllClients() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
