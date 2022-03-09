package com.paymybuddy.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.exception.YourBalanceIsNotEnough;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.TransactionRepository;

@Service
@Transactional
public class TransactionService
{
	private static Logger logger = LogManager.getLogger("TransactionServiceLog");
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	private UserService userService; 
	private BigDecimal percent = BigDecimal.valueOf(0.5);
	private LocalDateTime dateTime;
	
	/**
	 * Get list of all transactions with sender email :
	 * Find all transactions saved with user email
	 * 
	 * @return List<Transaction> The list of user transactions
	 */
	public List<Transaction> getTransactionsByUserId()
	{
		logger.info("Transactions list found for user");	
		return transactionRepository.findAll();
	}
		
	/**
	 * Get one transaction with user email :
	 * Find the transactions for sender
	 * 
	 * @return Transaction The transaction with email & id
	 */
	public List<Transaction> getTransactiondsBySender(User sender)
	{
		logger.info("The transactions for {} is found",sender);
		return transactionRepository.getTransactionsBySender(sender);
	}
	
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
	public synchronized void sendMoney(User sender, String email, String message, BigDecimal amount) throws YourBalanceIsNotEnough
	{
		User receiver = userService.getUserByEmail(email);
		BigDecimal quantityAfterPercent = amount.add(amount.multiply(percent));
		BigDecimal fromCurrentBalance = sender.getBalance();
		BigDecimal toCurrentBalance = receiver.getBalance();
		
		if (fromCurrentBalance.compareTo(quantityAfterPercent) < 0)
		{
			throw new YourBalanceIsNotEnough();
		}
		sender.setBalance(fromCurrentBalance.subtract(quantityAfterPercent));
		receiver.setBalance(toCurrentBalance.add(amount));
		Transaction transaction = new Transaction(sender, receiver, dateTime, amount, message, percent);
		transactionRepository.save(transaction);
	}
}