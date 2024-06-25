package com.devsu.account.model.dto;

import com.devsu.account.model.AccountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountDto implements Serializable {

    private Long id;

    @NotEmpty
    @Size(max = 20, message = "Number cannot exceed 10 digits")
    private String number;

    @NotNull
    private AccountType accountType;

    @NotNull
    @PositiveOrZero(message = "Initial balance must be equal or greater than 0")
    private BigDecimal initialBalance;

    @NotNull
    private Boolean status;

    private Long clientId;

    @NotNull
    private String clientIdentification;

    private List<MovementDto> movements;

    @Serial
    private static final long serialVersionUID = -4861018079796432111L;
}
