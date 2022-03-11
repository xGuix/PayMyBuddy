package com.paymybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.BankAccount;

/**
 * Bank Account Interface JPA Repository
 */
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>
{}