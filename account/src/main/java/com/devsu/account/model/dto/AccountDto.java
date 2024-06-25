package com.devsu.account.model.dto;

import com.devsu.account.model.AccountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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

    @NotNull
    private Long clientId;

    private List<MovementDto> movements;

    public AccountDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<MovementDto> getMovements() {
        return movements;
    }

    public void setMovements(List<MovementDto> movements) {
        this.movements = movements;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", accountType=" + accountType +
                ", initialBalance=" + initialBalance +
                ", status=" + status +
                ", clientId=" + clientId +
                ", movements=" + movements +
                '}';
    }

    @Serial
    private static final long serialVersionUID = -4861018079796432111L;
}
