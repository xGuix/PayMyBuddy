package com.paymybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>
{
	BankAccount saveAndFlush(BankAccountDTO bankaccountDto);
}