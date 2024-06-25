package com.devsu.account.controller;

import com.devsu.account.model.dto.AccountDto;
import com.devsu.account.model.dto.StatusReportDto;
import com.devsu.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller for accounts request
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAccount(@Valid @RequestBody AccountDto cuentaDto) {
        service.saveAccount(cuentaDto);
    }

    @PostMapping("/save-list")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAccountsList(@Valid @RequestBody List<AccountDto> accountDtos) {
        service.saveAccountsList(accountDtos);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDto accountDto) {
        service.updateAccount(id, accountDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> findAllAccounts() {
        return ResponseEntity.ok(service.findAllAccounts());
    }

    @GetMapping("/report")
    public ResponseEntity<StatusReportDto> generateStatusReport(
            @RequestParam(name = "initialDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam Long clientId) {
        return ResponseEntity.ok(service.generateStatusReport(initialDate, endDate, clientId));
    }
}
