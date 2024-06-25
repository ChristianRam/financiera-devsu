package com.devsu.client.mapper;


import com.devsu.client.model.Client;
import com.devsu.client.model.dto.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {


    ClientDto toDto(Client client);

    Client toEntity(ClientDto clientDto);
}
