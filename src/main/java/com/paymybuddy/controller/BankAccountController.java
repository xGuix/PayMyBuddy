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

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.service.BankAccountService;

@RestController
public class BankAccountController
{
	private static Logger logger = LogManager.getLogger("BankAccountControllerLog");
	
	@Autowired
	private BankAccountService bankAccountService;

	/**
	 * Read all Bank accounts :
	 * Get all Bank accounts
	 * 
	 * @return BankAccount The full bank accounts list
	 */
	@GetMapping(value = "/bankaccounts")
	public ResponseEntity<List<BankAccount>> getBankAccounts()
	{
		List<BankAccount> bankAccountList = bankAccountService.getBankAccounts();

		logger.info("Get all bank account list");			
		return new ResponseEntity<>(bankAccountList, HttpStatus.FOUND);
	}
		
	/**
	 * Get one bank account by email
	 * 
	 * @return BankAccount The bank account with email
	 */
	@GetMapping(value = "/bankaccount")
	public ResponseEntity<BankAccount> getBankAccountById(Integer bankaccountId)
	{
		BankAccount bankByEmail = bankAccountService.getBankAccountById(bankaccountId);
		
		logger.info("Get user with id= {}", bankaccountId);	
		return new ResponseEntity<>(bankByEmail, HttpStatus.FOUND);
	}	
	
	/**
	 * Add one bank account to list
	 * 
	 * @return BankAccount The bank account saved
	 */
	@PostMapping(value = "/bankaccount")
	public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bankAccount)
	{	
		BankAccount bankToAdd = null;
		if(bankAccountService.getBankAccountById(bankAccount.getBankaccountId())==null)
		{
			bankToAdd = bankAccountService.addBankAccount(bankAccount);
			logger.info("User added is: {}",bankToAdd);
		}
		else {
			logger.info("Bank Account already exists: {}", bankAccount);	
		}
		return new ResponseEntity<>(bankAccount, HttpStatus.CREATED);
	}
	
	/**
	 * Update one bank account of list
	 * 
	 * @return BankAccount The bank account updated
	 */
	@PutMapping(value = "/bankaccount")
	public ResponseEntity<BankAccount> updateBankAccount(
			@RequestParam Integer bankaccountId, @RequestBody BankAccount bankAccount)
	{	
		BankAccount bankToUpdate = null;
		if(getBankAccountById(bankaccountId)!=null)
		{
			bankToUpdate = bankAccountService.updateBankAccount(bankaccountId, bankAccount);
			logger.info("User updated: {}", bankToUpdate);	
		}
		else {
			logger.info("User does not exists: {}", bankToUpdate);	
		}
		return new ResponseEntity<>(bankToUpdate, HttpStatus.OK);
	}
	
	/**
	 * Delete one bank account from list
	 * 
	 * @return BankAccount Bank account deleted 
	 */
	@DeleteMapping(value = "/bankaccount")
	public ResponseEntity<String> deleteBankAccount(Integer bankaccountId)
	{
		String info = null;
		BankAccount bankToDel = bankAccountService.getBankAccountById(bankaccountId);
		if (bankToDel != null)
		{
			info = bankToDel.getBankaccountId()+" "+bankToDel.getBankName()+" "+bankToDel.getIbanAccount();		
			bankAccountService.deleteBankAccountById(bankaccountId);
			logger.info("Bank account: {} have been deleted", info);
		}
		else {
			logger.info("BankAccount does not exists! Result is: {}", info);
		}
		return new ResponseEntity<>(info + ": BankAccount have been deleted", HttpStatus.OK);
	}
}