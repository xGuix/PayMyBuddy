package com.paymybuddy.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.model.BankAccount;

/**
 * Interface for Bank account service
 */
public interface IBankAccountService extends UserDetailsService 
{
	/**
	 * {@inheritDoc}
	 */
	BankAccount addBankAccount(BankAccountDTO newBankAccount);
	
	/**
	 * {@inheritDoc}
	 */
	BankAccount updateBankAccount(Integer bankaccountId, BankAccount findAccount);
}