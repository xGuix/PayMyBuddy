package com.paymybuddy.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.repository.BankAccountRepository;

@Service
public class BankAccountService
{
	private static Logger logger = LogManager.getLogger("BankAccountServiceLog");
	
	@Autowired
	private BankAccountRepository bankAccountRepository;
   
	/**
	 * Get list of all bank account :
	 * Find all Bank account saved
	 * 
	 * @return List<BankAccount> The list of all bank account
	 */
	public List<BankAccount> getBankAccounts()
	{
		logger.info("Bank accounts list found");	
		return bankAccountRepository.findAll();
	}
	
	/**
	 * Get the bank account with email:
	 * Find the bank account of a user with email
	 * 
	 * @return BankAccount The bank account of user
	 */
	public BankAccount getBankAccountById(Integer bankaccountId)
	{
		logger.info("Bank account found with Id: {}",bankaccountId);
		return bankAccountRepository.getById(bankaccountId);
	}
	
	/**
	 * Add bank account of user:
	 * Create & save the bank account of user with email
	 * 
	 * @return BankAccount The bank account added
	 */
	public BankAccount addBankAccount(BankAccount bankAccount)
	{	
		logger.info("Bank account add and saved");		
		return bankAccountRepository.saveAndFlush(bankAccount);
	}
	
	
	/**
	 * Update bank account of user:
	 * Modify & save the bank account of user by email
	 * 
	 * @return BankAccount The bank account updated
	 */
	public BankAccount updateBankAccount(Integer bankaccountId, BankAccount bankAccount)
	{	
		BankAccount baToUpdate = getBankAccountById(bankaccountId);
		if(baToUpdate!=null)
		{
			baToUpdate.setBankaccountId(bankAccount.getBankaccountId());
			baToUpdate.setBankName(bankAccount.getBankName());
			baToUpdate.setIbanAccount(bankAccount.getIbanAccount());

			bankAccountRepository.saveAndFlush(baToUpdate);
		}
		else {
			logger.info("Bank account does not exists!");
		}
		logger.info("Bank account update and saved");		
		return baToUpdate;
	}
	
	/**
	 * Delete bank account with userEmail :
	 * Delete bank account from user
	 */
	public void deleteBankAccountById(Integer bankaccountId)
	{
		bankAccountRepository.deleteById(bankaccountId);
		logger.info("Bank account have been deleted");
	}
}