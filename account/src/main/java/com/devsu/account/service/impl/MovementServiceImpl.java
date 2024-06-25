package com.devsu.account.service.impl;

import com.devsu.account.exception.BadRequestException;
import com.devsu.account.exception.NotFoundException;
import com.devsu.account.mapper.MovementMapper;
import com.devsu.account.model.MovementType;
import com.devsu.account.model.dto.AccountDto;
import com.devsu.account.model.dto.MovementDto;
import com.devsu.account.repository.MovementRepository;
import com.devsu.account.service.AccountService;
import com.devsu.account.service.MovementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovementServiceImpl implements MovementService {

    private static final String MOVEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "Movement with ID %s not found";

    private static final String ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE = "Active account with number %s not found";

    private final MovementRepository repository;

    private final MovementMapper mapper;

    private final AccountService accountService;

    public MovementServiceImpl(MovementRepository repository, MovementMapper mapper, AccountService accountService) {
        this.repository = repository;
        this.mapper = mapper;
        this.accountService = accountService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveMovement(MovementDto movementDto) {
        log.info("Adding new movement for account with number: {}", movementDto.getAccountNumber());

        AccountDto accountDto = findAccountByNumber(movementDto.getAccountNumber());
        movementDto.setAccountId(accountDto.getId());
        validateMovement(movementDto, accountDto);

        BigDecimal initialBalance = accountDto.getInitialBalance();

        movementDto.setBalance(initialBalance);
        repository.save(mapper.toEntity(movementDto));

        log.info("Updating initial balance for account with ID: {}", movementDto.getAccountId());

        accountDto.setInitialBalance(initialBalance.add(movementDto.getValue()));
        accountService.updateAccount(accountDto.getId(), accountDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveMovementsList(List<MovementDto> movementDtos) {
        movementDtos.forEach(this::saveMovement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateMovement(Long id, MovementDto movementDto) {
        log.info("Updating a movement for account with ID: {}", movementDto.getAccountId());
        findMovementById(id)
                .orElseThrow(() -> new NotFoundException(String.format(MOVEMENT_NOT_FOUND_EXCEPTION_MESSAGE, id)));

        AccountDto accountDto = findAccountByNumber(movementDto.getAccountNumber());
        movementDto.setAccountId(accountDto.getId());
        movementDto.setId(id);
        repository.save(mapper.toEntity(movementDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMovement(Long id) {
        log.info("Deleting movement with ID: {}", id);
        findMovementById(id)
                .orElseThrow(() -> new NotFoundException(String.format(MOVEMENT_NOT_FOUND_EXCEPTION_MESSAGE, id)));
        repository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MovementDto> findMovementById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MovementDto> findAllMovements() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    private void validateMovement(MovementDto movementDto, AccountDto accountDto) {
        MovementType movementType = movementDto.getMovementType();
        BigDecimal movementValue = movementDto.getValue();
        BigDecimal initialBalance = accountDto.getInitialBalance();

        if (movementValue.compareTo(BigDecimal.ZERO) == 0) {
            throw new BadRequestException("Movement value must not be 0");
        }

        if (MovementType.WITHDRAWAL.equals(movementType)) {
            if(movementValue.compareTo(BigDecimal.ZERO) > 0) {
                throw new BadRequestException("Value must be negative to perform a WITHDRAWAL");
            }

            if (initialBalance.compareTo(movementValue.abs()) < 0) {
                throw new BadRequestException("You don't have the balance available to make this movement");
            }
        } else if (MovementType.DEPOSIT.equals(movementType) && movementValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("The value must be positive to perform a DEPOSIT");
        }
    }

    private AccountDto findAccountByNumber(String accounNumber) {
        return accountService.findAccountByNumber(accounNumber).filter(account -> account.getStatus().equals(true))
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE, accounNumber)));
    }
}
