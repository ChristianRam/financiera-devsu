package com.devsu.account.service.impl;

import com.devsu.account.feign.ClientFeignClient;
import com.devsu.account.exception.NotFoundException;
import com.devsu.account.mapper.AccountMapper;
import com.devsu.account.model.dto.AccountDto;
import com.devsu.account.model.dto.ClientDto;
import com.devsu.account.model.dto.StatusReportDto;
import com.devsu.account.repository.AccountRepository;
import com.devsu.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private static final String ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE = "Account with id %s not found";
    private static final String CLIENT_NOT_FOUND_EXCEPTION_MESSAGE = "Client with identification %s not found";

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
        log.info("Adding a new account for client with identification: {}", accountDto.getClientIdentification());
        ClientDto clientDto = findClientByIdentification(accountDto.getClientIdentification());
        accountDto.setClientId(clientDto.getId());

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

        if (Objects.isNull(accountDto.getClientId()) && !Objects.isNull(accountDto.getClientIdentification())) {
            ClientDto clientDto = findClientByIdentification(accountDto.getClientIdentification());
            accountDto.setClientId(clientDto.getId());
        }

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
    public Optional<AccountDto> findAccountByNumber(String number) {
        return repository.findByNumber(number).map(mapper::toDto);
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
    public StatusReportDto generateStatusReport(LocalDate initialDate, LocalDate endDate, String clientIdentification) {
        ClientDto clientDto = findClientByIdentification(clientIdentification);
        List<AccountDto> cuentaDtos = repository.findAccountsAndMovementsByClientIdAndDate(clientDto.getId(), initialDate, endDate)
                .stream().map(mapper::toDto).toList();

        StatusReportDto statusReportDto = new StatusReportDto(clientDto.getName(), cuentaDtos);
        log.info("Status Report: {}", statusReportDto);
        return statusReportDto;
    }

    private ClientDto findClientByIdentification(String clientIdentification) {
        return Objects.requireNonNull(client.findClientByIdentification(clientIdentification).getBody())
                .orElseThrow(() -> new NotFoundException(String.format(CLIENT_NOT_FOUND_EXCEPTION_MESSAGE, clientIdentification)));
    }
}
