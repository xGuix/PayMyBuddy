package com.paymybuddy.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.repository.TransactionRepository;

@Service
public class TransactionService
{
	private static Logger logger = LogManager.getLogger("UserFriendsServiceLog");
	
	@Autowired
	private TransactionRepository transactionRepository;
	    
	public List<Transaction> getTransactions()
	{
		logger.info("Transactions list found");	
		return transactionRepository.findAll();
	}
	
	public Optional<Transaction> getTransactiondByEmail(String sender)
	{
		logger.info("Transaction found with email: {}",sender);
		return transactionRepository.findByEmail(sender);
	}
	
	public Transaction addTransaction(String sender,String receiver, Transaction transaction)
	{
		Transaction addConnection = new Transaction();
		addConnection.setSender(sender);
		addConnection.setReceiver(receiver);
		addConnection.setAmount(transaction.getAmount());
		addConnection.setDescription(transaction.getDescription());
		addConnection.setTranscationFee(transaction.getTranscationFee());
		
		logger.info("Transaction add and saved");		
		return transactionRepository.save(addConnection);
	}
		
	public void deleteTransactionById(Integer id)
	{
		transactionRepository.deleteById(id);
		logger.info("Transaction have been deleted");
	}
}