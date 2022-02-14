package com.paymybuddy.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.BankAccountRepository;

@Service
public class BankAccountService
{
	private static Logger logger = LogManager.getLogger("BankAccountServiceLog");
	
	@Autowired
	private BankAccountRepository bankAccountRepository;
   
	/**
	 * Convert User Entity in bank account :
	 * Get Bank account user without password and friend list
	 * 
	 * @return BankAccount The user simplify
	 */
    public BankAccount entityToSimpleUser(BankAccount bankAccount)
    {
    	User user = bankAccount.getUser();
		User simpleUser = new User(
				user.getUserId(),
				user.getFirstname(),
				user.getLastname(),
				user.getEmail(),
				user.getBalance()); 
    	bankAccount.setUser(simpleUser);
    	  	
    	return bankAccount;
    }
	/**
	 * Get list of all bank account :
	 * Find all Bank account saved
	 * 
	 * @return List<BankAccount> The list of all bank account
	 */
	public List<BankAccount> getBankAccounts()
	{
		List<BankAccount> allAccount = bankAccountRepository.findAll();
		for (BankAccount ba : allAccount) 
		{	
			this.entityToSimpleUser(ba);		
			allAccount.set(allAccount.indexOf(ba),ba);
		}	
		logger.info("Bank accounts list found");	
		return allAccount;
	}
	
	/**
	 * Get the bank account for user:
	 * Find the bank account of a user with user id
	 * 
	 * @return BankAccount The bank account of the user
	 */
	public BankAccount getBankAccountByUser(Integer userId)
	{
		BankAccount newBankAccount = bankAccountRepository.getById(userId);
		//this.entityToSimpleUser(bankAccountRepository.getById(userId));
		
		logger.info("Bank account found with Id: {}",userId);
		return this.entityToSimpleUser(newBankAccount);
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
	public BankAccount updateBankAccount(Integer userId, BankAccount bankAccount)
	{	
		BankAccount baToUpdate = getBankAccountByUser(userId);
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