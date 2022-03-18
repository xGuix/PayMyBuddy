package com.paymybuddy.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddy.exception.BalanceNotEnough;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;

/**
 * Interface for transaction service
 */
public interface ITransactionService extends UserDetailsService
{
	/**
	 * {@inheritDoc}
	 */
	List<Transaction> getTransactiondsBySender(User userActiv);

	/**
	 * {@inheritDoc}
	 */
	List<Transaction> getTransactiondsByReceiver(User userActiv);

	/**
	 * {@inheritDoc}
	 * 
	 * @throws BalanceNotEnough 
	 */
	Transaction sendMoney(User userActiv, String email, String message, BigDecimal amount) throws BalanceNotEnough;
}