package com.devsu.account.model.dto;

import java.io.Serial;
import java.io.Serializable;

public class ClientDto implements Serializable {

    private Long id;

    private String name;

    public ClientDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Serial
    private static final long serialVersionUID = -2254731766646547196L;
}