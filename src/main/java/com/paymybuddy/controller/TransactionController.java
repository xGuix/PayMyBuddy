package com.paymybuddy.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.service.TransactionService;

@RestController
public class TransactionController
{
	private static Logger logger = LogManager.getLogger("TransactionControllerLog");
	
	@Autowired
	private TransactionService transactionService;

	/**
	 * Read all transactions :
	 * Get all transactions
	 * 
	 * @return TransactionsList The full transactions list
	 */
	@GetMapping(value = "/transactions")
	public ResponseEntity<List<Transaction>> getTransactions()
	{
		List<Transaction> transactionsList = transactionService.getTransactions();

		logger.info("Get all transactions list");			
		return new ResponseEntity<>(transactionsList, HttpStatus.FOUND);
	}
		
//	/**
//	 * Get all transactions by user
//	 * 
//	 * @return Transaction The transaction with email
//	 */
//	@GetMapping(value = "/transactionsUser")
//	public ResponseEntity<List<Transaction>> getTransactionBySender(User sender)
//	{
//		List<Transaction> transactionByEmail = transactionService.getTransactionsBySender(sender);
//		
//		logger.info("Get transaction for email = {} is found: {}", sender, transactionByEmail);	
//		return new ResponseEntity<>(transactionByEmail, HttpStatus.FOUND);
//	}
	
	/**
	 * Get one transactions by id
	 * 
	 * @return Transaction The transaction with id
	 */
	@GetMapping(value = "/transaction")
	public ResponseEntity<Transaction> getTransactionById(Integer transactionId)
	{
		Transaction transactionById = transactionService.getTransactiondById(transactionId);
		
		logger.info("Get user with id= {}", transactionById);	
		return new ResponseEntity<>(transactionById, HttpStatus.FOUND);
	}
	
	/**
	 * Add one transaction between two users
	 * 
	 * @return Transaction The transaction saved
	 */
	@PostMapping(value = "/transaction")
	public ResponseEntity<Transaction> addTransaction(@RequestParam String sender,@RequestParam String receiver, @RequestBody Transaction transaction)
	{
		Transaction newTransaction = transactionService.addTransaction(transaction);
		return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
	}
	
	/**
	 * Update one transaction by sender
	 * 
	 * @return Transaction The transaction changed
	 */
	@PutMapping(value = "/transaction")
	public ResponseEntity<Transaction> updateAddTransaction(
			@RequestParam Integer transactionId, @RequestBody Transaction transaction)
	{	
		Transaction transactionToUpdate = null;
		if(getTransactionById(transactionId)!=null)
		{
			transactionToUpdate = transactionService.updateTransaction(transactionId, transaction);
			logger.info("Transaction updated: {}", transactionToUpdate);	
		}
		else {
			logger.info("Transaction does not exists: {}", transactionToUpdate);	
		}
		return new ResponseEntity<>(transactionToUpdate, HttpStatus.OK);
	}
	
	/**
	 * Delete one Transaction
	 * 
	 * @return Transaction Transaction deleted 
	 */
	@DeleteMapping(value = "/transaction")
	public ResponseEntity<String> deleteBankAccount(Integer transactionId)
	{
		String info = null;
		Transaction transactionToDel = transactionService.getTransactiondById(transactionId);
		if (transactionToDel != null)
		{
			info = transactionToDel.getTransactionId()+" "
					+transactionToDel.getDateTime()+" "
					+transactionToDel.getSender()+" "
					+transactionToDel.getReceiver()+" "
					+transactionToDel.getAmount()+" "
					+transactionToDel.getDescription();		
			transactionService.getTransactiondById(transactionId);
			logger.info("Transaction: {} have been deleted", info);
		}
		else {
			logger.info("Transaction does not exists! Result is: {}", info);
		}
		return new ResponseEntity<>("Transaction have been deleted: "+info, HttpStatus.OK);
	}
}