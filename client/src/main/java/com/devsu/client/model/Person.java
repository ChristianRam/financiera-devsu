package com.devsu.client.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

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

    public Person() {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", identification='" + identification + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Serial
    private static final long serialVersionUID = -6875537261064380618L;
}
