package com.paymybuddy.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.exception.BalanceNotEnough;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.TransactionRepository;

import groovy.transform.Generated;

/**
 * Class Transaction service
 */
@Service
public class TransactionService implements ITransactionService
{
	private static Logger logger = LogManager.getLogger("TransactionServiceLog");
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private IUserService userService; 
	
	private BigDecimal percent = BigDecimal.valueOf(0.005);
	private LocalDateTime dateTime = LocalDateTime.now();

	/**
	 * Load User details
	 * 
	 * @param username String username
	 * @return UserDetails User details 
	 */
	@Override
	@Generated
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userService.getUserByEmail(username);
		if(user == null)
		{
			throw new UsernameNotFoundException("Invalid email or password.");
		}
		return  new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
	}
	
	/**
	 * Get list of all transactions sent :
	 * Find the transactions for sender
	 * 
	 * @param sender User sender
	 * @return TransactionList The transactions of user 
	 */
	public List<Transaction> getTransactiondsBySender(User sender)
	{
		logger.info("The transactions for {} is found",sender);
		return transactionRepository.getTransactionsBySender(sender);
	}
	
	/**
	 * Get list of all transactions received :
	 * Find the transactions for sender
	 * 
	 * @param receiver User receiver
	 * @return TransactionList The transactions of user 
	 */
	public List<Transaction> getTransactiondsByReceiver(User receiver)
	{
		logger.info("The transactions from {} is found",receiver);
		return transactionRepository.getTransactionsByReceiver(receiver);
	}

	/**
	 * Send money to friend :
	 * check both balance and calculate figures
	 * 
	 * @param sender User
	 * @param email String
	 * @param message String
	 * @param amount BigDecimal
	 * 
	 * @return void No return
	 * @throws BalanceNotEnough Exception class
	 */
	public Transaction sendMoney(User sender, String email, String message, BigDecimal amount) throws BalanceNotEnough
	{
		User receiver = userService.getUserByEmail(email);
		BigDecimal transactionFee = amount.multiply(percent);
		BigDecimal receiveAmount = amount.subtract(transactionFee);
		BigDecimal senderBalance = sender.getBalance();
		BigDecimal receiverBalance = receiver.getBalance();
		
		if (senderBalance.compareTo(receiveAmount) < 0)
		{
			logger.info("Balance is not enough: {}, sending amount: {}", senderBalance, amount);
			throw new BalanceNotEnough();
		}
		
		sender.setBalance(senderBalance.subtract(amount));
		receiver.setBalance(receiverBalance.add(receiveAmount));
		Transaction transaction = new Transaction(sender, receiver, dateTime, receiveAmount, message, transactionFee);
		
		logger.info("Transaction to save: {}",transaction);
		transactionRepository.save(transaction);
		return transaction;
	}
}