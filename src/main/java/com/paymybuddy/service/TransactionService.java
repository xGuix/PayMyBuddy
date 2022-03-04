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
import com.paymybuddy.repository.BankAccountRepository;
import com.paymybuddy.repository.TransactionRepository;

@Service
@Transactional
public class TransactionService
{
	private static Logger logger = LogManager.getLogger("TransactionServiceLog");
	
	@Autowired
	private TransactionRepository transactionRepository;
	private BankAccountRepository bankAccountRepository;
	 
	private BigDecimal PERCENT = BigDecimal.valueOf(0.5);
	private LocalDateTime dateTime;
//	/**
//	 * Get list of all transactions :
//	 * Find all transactions saved
//	 * 
//	 * @return List<Transaction> The list of all transactions
//	 */
//	public List<Transaction> getTransactions()
//	{
//		logger.info("Transactions list found");	
//		return transactionRepository.findAll();
//	}
	
	/**
	 * Get list of all transactions with sender email :
	 * Find all transactions saved with user email
	 * 
	 * @return List<Transaction> The list of user transactions
	 */
	public List<Transaction> getTransactionsForUser(Iterable<Integer> userId)
	{
		logger.info("Transactions list found for user");	
		return transactionRepository.findAllById(userId);
	}
	
	public synchronized void makePayment(User sender, User receiver, String description, BigDecimal amount) throws YourBalanceIsNotEnough
	{
		BigDecimal quantityAfterPercent = amount.add(amount.multiply(PERCENT));
		BigDecimal fromCurrentBalance = sender.getBalance();
		BigDecimal toCurrentBalance = receiver.getBalance();
		
		if (fromCurrentBalance.compareTo(quantityAfterPercent) < 0)
		{
			throw new YourBalanceIsNotEnough();
		}
		sender.setBalance(fromCurrentBalance.subtract(quantityAfterPercent));
		receiver.setBalance(toCurrentBalance.add(amount));
		bankAccountRepository.save(sender);
		bankAccountRepository.save(receiver);
		Transaction transaction = new Transaction(sender, receiver, dateTime, amount, description, PERCENT);
		transactionRepository.save(transaction);
	}
	
//	/**
//	 * Get one transaction with user email :
//	 * Find the transactions for sender
//	 * 
//	 * @return Transaction The transaction with email & id
//	 */
//	public Transaction getTransactiondBySender(String sender)
//	{
//		logger.info("The transactions for {} is found",sender);
//		return transactionRepository.getByEmailSender(sender);
//	}
	
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
	 * Add a new transaction :
	 * Create & save transaction in list
	 * 
	 * @return Transaction The transaction added
	 */
	public Transaction updateTransaction(Integer transactionId, Transaction transaction)
	{
		Transaction newTransaction = new Transaction();
		newTransaction.setSender(transaction.getReceiver());
		newTransaction.setReceiver(transaction.getReceiver());
		newTransaction.setAmount(transaction.getAmount());
		newTransaction.setDescription(transaction.getDescription());
		newTransaction.setTranscationFee(transaction.getTranscationFee());
		
		logger.info("Transaction update and saved");		
		return transactionRepository.saveAndFlush(newTransaction);
	}
	
	/**
	 * Delete transaction from list :
	 * Delete a transaction with id
	 */
	public void deleteTransactionById(Integer transactionId)
	{
		transactionRepository.deleteById(transactionId);
		logger.info("Transaction have been deleted");
	}
}