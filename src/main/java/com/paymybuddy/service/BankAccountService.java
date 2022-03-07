package com.paymybuddy.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.BankAccountRepository;

@Service
public class BankAccountService
{
	private static Logger logger = LogManager.getLogger("BankAccountServiceLog");
	
	@Autowired
	private BankAccountRepository bankAccountRepository;
   
//	/**
//	 * Convert User Entity in bank account :
//	 * Get Bank account user without password and friend list
//	 * 
//	 * @return BankAccount The user simplify
//	 */
//    public BankAccount entityToSimpleDto(BankAccountDTO bankAccountDto)
//    {
//    	User user = bankAccountDto.getUser();
//		User simpleUser = new User(
//				user.getUserId(),
//				user.getFirstname(),
//				user.getLastname(),
//				user.getEmail(),
//				user.getBalance()); 
//		bankAccountDto.setUser(simpleUser);
//		BankAccount bankAccount = new  ;
//    	  	
//    	return bankAccount;
//    }
//    
//	/**
//	 * Get list of all bank account :
//	 * Find all Bank account saved
//	 * 
//	 * @return List<BankAccount> The list of all bank account
//	 */
//	public List<BankAccount> getBankAccounts()
//	{
//		List<BankAccount> allAccount = bankAccountRepository.findAll();
//		for (BankAccount ba : allAccount) 
//		{	
//			this.entityToSimpleUser(ba);		
//			allAccount.set(allAccount.indexOf(ba),ba);
//		}	
//		logger.info("Bank accounts list found");	
//		return allAccount;
//	}
	
	/**
	 * Get the bank account for user:
	 * Find the bank account of a user with user id
	 * 
	 * @return BankAccount The bank account of the user
	 */
	public BankAccount getBankAccountById(Integer bankId)
	{
		BankAccount newBankAccount = bankAccountRepository.getById(bankId);

		logger.info("Bank account found with Id: {}",bankId);
		return newBankAccount;
	}
	
	/**
	 * Add bank account of user:
	 * Create & save the bank account of user with email
	 * 
	 * @return BankAccount The bank account added
	 */
	public BankAccount addBankAccount(BankAccountDTO bankAccountDto)
	{	
		User checkUser = bankAccountDto.getUser();
		BankAccount checkBankAccount = checkUser.getBankAccount();
		if(checkBankAccount == null)
		{
			BankAccount newBankAccount = new BankAccount(
					bankAccountDto.getIbanAccount(),
					bankAccountDto.getBankName(),
					bankAccountDto.getUser()
					);
			
			checkUser.setBankAccount(newBankAccount);
			logger.info("Bank account add and saved for user");
			return bankAccountRepository.saveAndFlush(newBankAccount);
		}
		logger.info("Already one registered bank account");
		return checkBankAccount;
	}
	
	
	/**
	 * Update bank account of user:
	 * Modify & save the bank account of user by email
	 * 
	 * @return BankAccount The bank account updated
	 */
	public BankAccount updateBankAccount(Integer bankId, BankAccount findAccount)
	{	
		BankAccount baToUpdate = getBankAccountById(bankId);
		if(baToUpdate!=null)
		{
			baToUpdate.setBankName(findAccount.getBankName());
			baToUpdate.setIbanAccount(findAccount.getIbanAccount());

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
	public void deleteBankAccountById(Integer bankId)
	{
		bankAccountRepository.deleteById(bankId);
		logger.info("Bank account have been deleted");
	}
}