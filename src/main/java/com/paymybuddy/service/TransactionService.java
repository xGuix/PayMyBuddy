package com.paymybuddy.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.exception.YourBalanceIsNotEnough;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.TransactionRepository;

@Service
public class TransactionService
{
	private static Logger logger = LogManager.getLogger("TransactionServiceLog");
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private IUserService userService; 
	
	private BigDecimal percent = BigDecimal.valueOf(0.05);
	private LocalDateTime dateTime = LocalDateTime.now();
	
	/**
	 * Get one transaction with transactionId :
	 * Find the transactions id
	 * 
	 * @return Transaction The transaction with id
	 */
	public Transaction getTransactiondById(Integer transactionId)
	{
		logger.info("The transaction N°{} is found}",transactionId);
		return transactionRepository.getById(transactionId);
	}
	
	/**
	 * Get list of all transactions sent :
	 * Find the transactions for sender
	 * 
	 * @return List<Transaction> The transactions of user 
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
	 * @return List<Transaction> The transactions of user 
	 */
	public List<Transaction> getTransactiondsByReceiver(List<User> friendList)
	{
		logger.info("The transactions from {} is found",friendList);
		return transactionRepository.getTransactionsByReceiver(friendList);
	}
	
	/**
	 * Add a new transaction :
	 * Create & save transaction in list
	 * 
	 * @return Transaction The transaction added
	 */
	public Transaction addTransaction(Transaction transaction)
	{	
		logger.info("Transaction add and saved");		
		return transactionRepository.save(transaction);
	}

	/**
	 * Send money to friend :
	 * check both balance and calculate figures
	 * 
	 * @return void No return
	 */
	public Transaction sendMoney(User sender, String email, String message, BigDecimal amount) throws YourBalanceIsNotEnough
	{
		User receiver = userService.getUserByEmail(email);
		BigDecimal transactionFee = amount.multiply(percent);
		BigDecimal receiveAmount = amount.subtract(transactionFee);
		BigDecimal senderBalance = sender.getBalance();
		BigDecimal receiverBalance = receiver.getBalance();
		
		if (senderBalance.compareTo(receiveAmount) < 0)
		{
			logger.info("Balance is not enough: {}, sending amount: {}", senderBalance, amount);
			throw new YourBalanceIsNotEnough();
		}
		
		sender.setBalance(senderBalance.subtract(amount));
		receiver.setBalance(receiverBalance.add(receiveAmount));
		Transaction transaction = new Transaction(sender, receiver, dateTime, amount, message, transactionFee);
		
		logger.info("Transaction to save: {}",transaction);
		transactionRepository.save(transaction);
		return transaction;
	}
}