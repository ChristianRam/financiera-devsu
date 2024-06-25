package com.devsu.client.model.dto;

import com.devsu.client.model.Gender;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public class ClientDto implements Serializable {

    private Long id;

    @NotEmpty
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @NotNull
    private Gender gender;

    @NotNull
    private Integer age;

    @NotEmpty
    @Size(max = 10, message = "Identification cannot exceed 10 digits")
    private String identification;

    @NotEmpty
    @Size(max = 45, message = "Address cannot exceed 45 characters")
    private String address;

    @NotEmpty
    @Size(max = 10, message = "Phone cannot exceed 10 digits")
    private String phone;

    @NotNull
    @Digits(integer = 4, fraction = 0, message = "Password must be 4 digits")
    private Integer password;

    @NotNull
    private Boolean status;

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
        return "ClientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", identification='" + identification + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", password=" + password +
                ", status=" + status +
                '}';
    }

    @Serial
    private static final long serialVersionUID = 3809228335220853768L;
}
