package com.devsu.account.model.dto;

import com.devsu.account.model.MovementType;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MovementDto implements Serializable {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private MovementType movementType;

    @NotNull
    private BigDecimal value;

    private BigDecimal balance;

    @NotNull
    private Long accountId;

    public MovementDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Serial
    private static final long serialVersionUID = -3976503473674705641L;
}
