package com.devsu.account.service;

import com.devsu.account.model.dto.AccountDto;
import com.devsu.account.model.dto.StatusReportDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    /**
     * Save a new account
     *
     * @param accountDto account to save
     */
    void saveAccount(AccountDto accountDto);

    /**
     * Save an account list
     *
     * @param accountDtos accounts to save
     */
    void saveAccountsList(List<AccountDto> accountDtos);

    /**
     * Update an account by id
     *
     * @param id account identifier
     * @param accountDto account to update
     */
    void updateAccount(Long id, AccountDto accountDto);

    /**
     * Delete an account
     * @param id account identifier
     */
    void deleteAccount(Long id);

    /**
     * Retrieves an account by id if exists, otherwise return an empty optional
     *
     * @param id account identifier
     * @return account optional
     */
    Optional<AccountDto> findAccountById(Long id);

    /**
     * Return all accounts
     *
     * @return accounts list
     */
    List<AccountDto> findAllAccounts();

    /**
     * Generates a report with client accounts and movements information
     *
     * @param initialDate initial date range
     * @param endDate end date range
     * @param clientId client identifier
     * @return report with information
     */
    StatusReportDto generateStatusReport(LocalDate initialDate, LocalDate endDate, Long clientId);
}
