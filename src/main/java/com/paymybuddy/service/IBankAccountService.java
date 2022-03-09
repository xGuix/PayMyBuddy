package com.paymybuddy.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.model.BankAccount;

public interface IBankAccountService extends UserDetailsService 
{
	BankAccount addBankAccount(BankAccountDTO newBankAccount);
	BankAccount updateBankAccount(Integer bankaccountId, BankAccount findAccount);
}