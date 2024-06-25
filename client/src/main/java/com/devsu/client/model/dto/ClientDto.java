package com.devsu.client.model.dto;

import com.devsu.client.model.Gender;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
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

    @Serial
    private static final long serialVersionUID = 3809228335220853768L;
}
