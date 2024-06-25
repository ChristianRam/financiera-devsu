package com.devsu.client.service.impl;


import com.devsu.client.exception.NotFoundException;
import com.devsu.client.mapper.ClientMapper;
import com.devsu.client.model.dto.ClientDto;
import com.devsu.client.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClientMapper clienteMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void givenNonExistentClient_WhenCallUpdateClient_ThenReturnNotFoundException() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> clientService.updateClient(1L, new ClientDto()));

        verify(repository).findById(any());
    }
}