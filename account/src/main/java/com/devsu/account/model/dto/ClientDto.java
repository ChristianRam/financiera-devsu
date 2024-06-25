package com.devsu.account.model.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ClientDto implements Serializable {

    private Long id;

    private String name;


    @Serial
    private static final long serialVersionUID = -2254731766646547196L;
}