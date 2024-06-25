package com.devsu.client.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "clients")
@PrimaryKeyJoinColumn(referencedColumnName = "person_id", name = "client_id")
public class Client extends Person implements Serializable {

    @Column(length = 4, nullable = false)
    private Integer password;

    @Column(nullable = false)
    private Boolean status;

    @Serial
    private static final long serialVersionUID = -7817601801693766201L;
}