package com.devsu.client.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 30, nullable = false)
    private Gender gender;

    @Column(length = 3, nullable = false)
    private Integer age;

    @Column(length = 10, nullable = false, unique = true)
    private String identification;

    @Column(length = 45, nullable = false)
    private String address;

    @Column(length = 10, nullable = false)
    private String phone;

    @Serial
    private static final long serialVersionUID = -6875537261064380618L;
}
