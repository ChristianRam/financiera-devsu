package com.devsu.account.model.dto;

import com.devsu.account.model.MovementType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovementDto implements Serializable {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private MovementType movementType;

    @NotNull
    private BigDecimal value;

    private BigDecimal balance;

    private Long accountId;

    @NotNull
    private String accountNumber;

    @Serial
    private static final long serialVersionUID = -3976503473674705641L;
}
