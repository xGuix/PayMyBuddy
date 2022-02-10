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
   
	public List<BankAccount> getBankAccounts()
	{
		logger.info("Bank accounts list found");	
		return bankAccountRepository.findAll();
	}
	
	public BankAccount getBankAccountByEmail(String userEmail)
	{
		logger.info("Bank account found with Email: {}",userEmail);
		return bankAccountRepository.getUserByEmail(userEmail);
	}
	
	public BankAccount addBankAccount(BankAccount bankAccount)
	{	
		logger.info("Bank account add and saved");		
		return bankAccountRepository.save(bankAccount);
	}
	
	public BankAccount updateBankAccount(String userEmail, BankAccount bankAccount)
	{	
		BankAccount baToUpdate = getBankAccountByEmail(userEmail);
		if(baToUpdate.getUserEmail().equals(bankAccount.getUserEmail()))
		{
			baToUpdate.setBankaccountId(bankAccount.getBankaccountId());
			baToUpdate.setBankName(bankAccount.getBankName());
			baToUpdate.setIbanAccount(bankAccount.getIbanAccount());

			bankAccountRepository.save(baToUpdate);
		}
		else {
			logger.info("Bank account does not exists!");
		}
		logger.info("Bank account update and saved");		
		return baToUpdate;
	}
	
	public void deleteBankAccountById(String userEmail)
	{
		bankAccountRepository.deleteByEmail(userEmail);
		logger.info("Bank account have been deleted");
	}
}