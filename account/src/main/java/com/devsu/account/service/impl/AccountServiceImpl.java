package com.devsu.account.service.impl;

import com.devsu.account.feign.ClientFeignClient;
import com.devsu.account.exception.NotFoundException;
import com.devsu.account.mapper.AccountMapper;
import com.devsu.account.model.dto.AccountDto;
import com.devsu.account.model.dto.ClientDto;
import com.devsu.account.model.dto.StatusReportDto;
import com.devsu.account.repository.AccountRepository;
import com.devsu.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private static final String ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE = "Account with id %s not found";
    private static final String CLIENT_NOT_FOUND_EXCEPTION_MESSAGE = "Client with id %s not found";

    private final AccountRepository repository;
    private final AccountMapper mapper;

    private final ClientFeignClient client;

    public AccountServiceImpl(AccountRepository repository, AccountMapper mapper, ClientFeignClient client) {
        this.repository = repository;
        this.mapper = mapper;
        this.client = client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAccount(AccountDto accountDto) {
        log.info("Adding a new account for client with ID: {}", accountDto.getClientId());
        findClientById(accountDto.getClientId());

        repository.save(mapper.toEntity(accountDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAccountsList(List<AccountDto> accountDtos) {
        accountDtos.forEach(this::saveAccount);
    }

    /**
     * {@inheritDoc}
     */
    public void updateAccount(Long id, AccountDto accountDto) {
        log.info("Updating account for client with id: {}", accountDto.getClientId());
        findAccountById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE, id)));
        accountDto.setId(id);
        repository.save(mapper.toEntity(accountDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAccount(Long id) {
        log.info("Deleting account with ID: {}", id);
        findAccountById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE, id)));
        repository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AccountDto> findAccountById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountDto> findAllAccounts() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StatusReportDto generateStatusReport(LocalDate initialDate, LocalDate endDate, Long clientId) {
        ClientDto clientDto = findClientById(clientId);
        List<AccountDto> cuentaDtos = repository.findAccountsAndMovementsByClientIdAndDate(clientId, initialDate, endDate)
                .stream().map(mapper::toDto).toList();

        StatusReportDto statusReportDto = new StatusReportDto(clientDto.getName(), cuentaDtos);
        log.info("Status Report: {}", statusReportDto);
        return statusReportDto;
    }

    private ClientDto findClientById(Long clientId) {
        return Objects.requireNonNull(client.findClientById(clientId).getBody())
                .orElseThrow(() -> new NotFoundException(String.format(CLIENT_NOT_FOUND_EXCEPTION_MESSAGE, clientId)));
    }
}
