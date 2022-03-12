package com.paymybuddy.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.exception.BalanceNotEnough;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.TransactionRepository;

/**
 * Test Class for Bank account service
 */
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest 
{
    @InjectMocks
    TransactionService transactionService;
    
    @Mock
    UserService userService;
    
    @Mock
    TransactionRepository transactionRepository;
   	
    Transaction transactionTest;
    List<Transaction> transactionSender;
    List<Transaction> transactionReceiver;
    
	User userTest;
	User userFriendTest;
	BigDecimal balance = BigDecimal.valueOf(1000.00);
	
	BigDecimal amount;
	BigDecimal transactionFee;
	LocalDateTime dateTime;
	String message ;

	@BeforeEach
	void setupTest()
	{
		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, null);
		userFriendTest = new User("Bob","Lazar","Groomlake","bl@paymybuddy.com", "Zone51", balance, null);
		
		message = "It is a transaction message test";
		amount = BigDecimal.valueOf(500.00);
		transactionSender = new ArrayList<>();
		transactionReceiver = new ArrayList<>();
		transactionTest = new Transaction(userTest, userFriendTest, dateTime, amount, message, transactionFee);
	}
	
	@Test
	void TestIfGetTransactiondsBySenderReturnTransactionList()
	{
		transactionSender.add(transactionTest);
		when(transactionRepository.getTransactionsBySender(userTest)).thenReturn(transactionSender);	
		
		List<Transaction> transactionsToTest = transactionService.getTransactiondsBySender(userTest);
		
		assertEquals(transactionSender, transactionsToTest);
		verify(transactionRepository, Mockito.times(1)).getTransactionsBySender(userTest);
	}
	
	@Test
	void TestIfGetTransactiondsByReceiverReturnTransactionsList()
	{
		transactionReceiver.add(transactionTest);
		when(transactionRepository.getTransactionsByReceiver(userTest)).thenReturn(transactionReceiver);	
		
		List<Transaction> transactionsToTest = transactionService.getTransactiondsByReceiver(userTest);
		
		assertEquals(transactionReceiver, transactionsToTest);
		verify(transactionRepository, Mockito.times(1)).getTransactionsByReceiver(userTest);
	}
	
	@Test
	void TestIfsendMoneyReturnTransaction() throws BalanceNotEnough
	{
		when(userService.getUserByEmail("bl@paymybuddy.com")).thenReturn(userFriendTest);
		
		Transaction transactionsToTest = transactionService.sendMoney(userTest,	userFriendTest.getEmail(), message, amount);
	
		assertEquals(userTest, transactionsToTest.getSender());
		assertEquals(userFriendTest, transactionsToTest.getReceiver());
		assertEquals(message, transactionsToTest.getDescription());
		assertEquals(BigDecimal.valueOf(475.00), transactionsToTest.getAmount().setScale(1));
		verify(transactionRepository, Mockito.times(1)).save(transactionsToTest);
	}
	
	@Test
	void TestIfsendMoneyReturnBalanceExecption() throws BalanceNotEnough
	{
		when(userService.getUserByEmail("bl@paymybuddy.com")).thenReturn(userFriendTest);
		
		transactionService.sendMoney(userTest,	userFriendTest.getEmail(), message, BigDecimal.valueOf(5000.00));
		assertThrows(BalanceNotEnough.class, () -> transactionTest.getAmount());
	}
}