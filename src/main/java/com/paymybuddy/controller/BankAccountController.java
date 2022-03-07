//package com.paymybuddy.controller;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.paymybuddy.dto.BankAccountDTO;
//import com.paymybuddy.model.BankAccount;
//import com.paymybuddy.service.BankAccountService;
//
//@RestController
//public class BankAccountController
//{
//	private static Logger logger = LogManager.getLogger("BankAccountControllerLog");
//	
//	@Autowired
//	private BankAccountService bankAccountService;
//
////	/**
////	 * Read all Bank accounts :
////	 * Get all Bank accounts
////	 * 
////	 * @return BankAccount The full bank accounts list
////	 */
////	@GetMapping(value = "/bankaccounts")
////	public ResponseEntity<List<BankAccount>> getBankAccounts()
////	{
////		List<BankAccount> bankAccountList = bankAccountService.getBankAccounts();
////
////		logger.info("Get all bank account list");			
////		return new ResponseEntity<>(bankAccountList, HttpStatus.FOUND);
////	}
//		
//	/**
//	 * Get one bank account by user id
//	 * 
//	 * @return BankAccount The bank account with user id
//	 */
//	@GetMapping(value = "/bankaccount")
//	public ResponseEntity<BankAccount> getBankAccountById(Integer bankId)
//	{
//		BankAccount bankByid = bankAccountService.getBankAccountById(bankId);
//		
//		logger.info("Get Bank account with id= {}", bankId);	
//		return new ResponseEntity<>(bankByid, HttpStatus.FOUND);
//	}	
//	
//	/**
//	 * Add one bank account to user
//	 * 
//	 * @return BankAccount The bank account saved
//	 */
//	@PostMapping(value = "/bankaccount")
//	public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccountDTO bankAccountDto)
//	{	
//		BankAccount bankToAdd = null;
//		
//		if(bankAccountService.getBankAccountById(bankAccountDto.getUser().getBankAccount().getBankaccountId())==null)
//		{
//			bankToAdd = bankAccountService.addBankAccount(bankAccountDto);
//			logger.info("Bank account added: {}",bankToAdd);
//		}
//		else {
//			logger.info("Bank Account already exists: {}", bankAccountDto);	
//		}
//		return new ResponseEntity<>(bankToAdd, HttpStatus.CREATED);
//	}
//	
//	/**
//	 * Update one bank account of list
//	 * 
//	 * @return BankAccount The bank account updated
//	 */
//	@PutMapping(value = "/bankaccount")
//	public ResponseEntity<BankAccount> updateBankAccount(
//			@RequestParam Integer bankId, @RequestBody BankAccount bankaccount)
//	{	
//		BankAccount bankToUpdate = null;
//		
//		if(getBankAccountById(bankId)!=null)
//		{
//			bankToUpdate = bankAccountService.updateBankAccount(bankId, bankaccount);
//			logger.info("Bank account updated: {}", bankToUpdate);	
//		}
//		else {
//			logger.info("Bank account does not exists: {}", bankToUpdate);	
//		}
//		return new ResponseEntity<>(bankToUpdate, HttpStatus.OK);
//	}
//	
//	/**
//	 * Delete one bank account from list
//	 * 
//	 * @return BankAccount Bank account deleted 
//	 */
//	@DeleteMapping(value = "/bankaccount")
//	public ResponseEntity<String> deleteBankAccount(Integer bankId)
//	{
//		String info = null;
//		BankAccount bankToDel = bankAccountService.getBankAccountById(bankId);
//		
//		if (bankToDel != null)
//		{
//			info = bankToDel.getBankaccountId()+" "+bankToDel.getBankName()+" "+bankToDel.getIbanAccount()+" "+bankToDel.getUser();		
//			bankAccountService.deleteBankAccountById(bankId);
//			logger.info("Bank account: {} have been deleted", info);
//		}
//		else {
//			logger.info("Bank account does not exists! Result is: {}", info);
//		}
//		return new ResponseEntity<>(info + ": Bank account have been deleted", HttpStatus.OK);
//	}
//}