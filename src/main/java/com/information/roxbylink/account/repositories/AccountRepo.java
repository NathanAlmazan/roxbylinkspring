package com.information.roxbylink.account.repositories;

import com.information.roxbylink.account.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepo extends JpaRepository<Account, Long> {

    @Modifying
    @Query(value = "INSERT INTO account (username, password) " +
            "VALUES (?1, ?2)", nativeQuery = true)
    void insertAccount(String username, String password);

    @Query(value = "SELECT * FROM account WHERE username = ?1", nativeQuery = true)
    Account getAccountByUsername(String username);

    @Query(value = "SELECT * FROM account WHERE account_id = ?1", nativeQuery = true)
    Account getAccountById(Long accountId);
}
