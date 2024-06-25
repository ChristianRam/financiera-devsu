package com.devsu.account.model;

import jakarta.persistence.*;
import jakarta.ws.rs.BadRequestException;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "number", length = 20, unique = true, nullable = false)
    private String number;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_type", length = 10, nullable = false)
    private AccountType accountType;

    @Column(name = "initial_balance", nullable = false)
    private BigDecimal initialBalance;

    @Column(nullable = false)
    private Boolean status;

    @Column(name = "client_id", nullable = false, updatable = false)
    private Long clientId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Movement> movements;

    public void setInitialBalance(BigDecimal initialBalance) {
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Initial balance cannot be less than 0");
        }
        this.initialBalance = initialBalance;
    }
    @Serial
    private static final long serialVersionUID = -2142901433411565547L;
}
