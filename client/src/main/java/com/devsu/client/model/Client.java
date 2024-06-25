package com.devsu.client.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "clients")
@PrimaryKeyJoinColumn(referencedColumnName = "person_id", name = "client_id")
public class Client extends Person implements Serializable {

    @Column(length = 4, nullable = false)
    private Integer password;

    @Column(nullable = false)
    private Boolean status;

    public Client() {
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Client{" +
                "password=" + password +
                ", status=" + status +
                '}';
    }

    @Serial
    private static final long serialVersionUID = -7817601801693766201L;
}