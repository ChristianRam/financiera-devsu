package com.devsu.account.repository;

import com.devsu.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT a.* FROM accounts a" +
            " JOIN movements m on a.account_id = m.account_id" +
            " WHERE a.client_id = ?1" +
            " AND m.date BETWEEN ?2 AND ?3", nativeQuery = true)
    List<Account> findAccountsAndMovementsByClientIdAndDate(Long clientId, LocalDate initialDate, LocalDate endDate);
}
