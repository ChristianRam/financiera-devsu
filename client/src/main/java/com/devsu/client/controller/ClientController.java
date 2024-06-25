package com.devsu.client.controller;

import com.devsu.client.model.dto.ClientDto;
import com.devsu.client.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for client request
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@Valid @RequestBody ClientDto clientDto) {
        service.saveClient(clientDto);
    }

    @PostMapping("/save-list")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClientsList(@Valid @RequestBody List<ClientDto> clientDtos) {
        service.saveClientList(clientDtos);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@PathVariable Long id, @Valid @RequestBody ClientDto clientDto) {
        service.updateClient(id, clientDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Long id) {
        service.deleteClient(id);
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> findAllClients() {
        return ResponseEntity.ok(service.findAllClients());
    }

    @GetMapping("/{identification}")
    public ResponseEntity<Optional<ClientDto>> findClientByIdentification(@PathVariable String identification) {
        return ResponseEntity.ok(service.findClientByIdentification(identification));
    }
}
