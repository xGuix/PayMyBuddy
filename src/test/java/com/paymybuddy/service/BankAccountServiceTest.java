package com.paymybuddy.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.BankAccountRepository;

/**
 * Test Class for Bank account service
 */
@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest 
{
	@MockBean
	AccessUserDetailService accessUserDetailService;
	
    @InjectMocks
    BankAccountService bankAccountService;
    
    @Mock
    BankAccountRepository bankAccountRepository;
   	
    BankAccount bankAccountTest;
    BankAccountDTO bankAccountDTOTest;
    String ibanAccount = "FR111333999222777";
    String bankName = "Banque de France";
	Integer accountId = 1;
    
	User userTest;
	BigDecimal balance = BigDecimal.ZERO;
	List<User> userListTest;
    
	@BeforeEach
	void setupTest()
	{
		userListTest = new ArrayList<>();
		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, userListTest);
		bankAccountTest = new BankAccount(ibanAccount, bankName, userTest);
		bankAccountDTOTest = new BankAccountDTO(userTest, ibanAccount, bankName);
	}
	
	@Test
	void TestIfGetBankAccountByIdReturnBankAccount()
	{
		when(bankAccountRepository.getById(1)).thenReturn(bankAccountTest);	
		BankAccount accountToTest = bankAccountService.getBankAccountById(accountId);	
		assertEquals(bankAccountTest, accountToTest);
		verify(bankAccountRepository, Mockito.times(1)).getById(accountId);
	}
	
	@Test
	void TestIfAddBankAccountReturnNull()
	{	
		BankAccount accountToAdd = bankAccountService.addBankAccount(bankAccountDTOTest);	
		assertNull(accountToAdd);
	}
	
	@Test
	void TestIfAddBankAccountReturnBankAccount()
	{	
		userTest.setBankAccount(bankAccountTest);
		BankAccount accountToAdd = bankAccountService.addBankAccount(bankAccountDTOTest);	
		assertEquals(bankAccountTest, accountToAdd);
	}
	
	@Test
	void TestIfUpdateBankAccountReturnNull()
	{	
		BankAccount accountToUpdate = bankAccountService.updateBankAccount(accountId, bankAccountTest);	
		assertNull(accountToUpdate);
	}
	
	@Test
	void TestIfUpdateBankAccountReturnBankAccount()
	{	
		when(bankAccountService.getBankAccountById(1)).thenReturn(bankAccountTest);	
		BankAccount accountToUpdate = bankAccountService.updateBankAccount(accountId, bankAccountTest);	
		assertEquals(bankAccountTest, accountToUpdate);
		verify(bankAccountRepository, Mockito.times(1)).saveAndFlush(accountToUpdate);
	}
}