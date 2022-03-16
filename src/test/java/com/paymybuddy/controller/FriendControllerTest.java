package com.paymybuddy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;
import com.paymybuddy.service.AccessUserDetailService;
import com.paymybuddy.service.IBankAccountService;
import com.paymybuddy.service.ITransactionService;
import com.paymybuddy.service.IUserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FriendController.class)
@WithMockUser(username="gb@paymybuddy.com")
class FriendControllerTest
{		
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccessUserDetailService accessUserDetailService;
	
	@MockBean
	private IUserService userService;

	@MockBean
	private IBankAccountService bankAccountService;
	
	@MockBean
	private ITransactionService transactionService;
			
	User userTest;
	BigDecimal balance;
	List<User> friendList;
	
	String firstnameTest;
	String lastnameTest;
	String cityTest;
	String emailTest;
	
    String ibanAccount;
    String bankName;
	BankAccount bankAccount;

	@BeforeEach
	void setup()
	{
		balance = BigDecimal.valueOf(100.00);
		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, friendList);
		
	    ibanAccount = "FR111333999222777";
	    bankName = "Banque de France";
		bankAccount= new BankAccount(ibanAccount,bankName,userTest);
		
		firstnameTest = "firstname";
		lastnameTest = "lastname";
		cityTest = "city";
		emailTest = "email@test.com";
	}

	@Test
	void getTransferReturnFriendpageWithFriendInfo() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		mockMvc.perform(get("/friend")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user"))
	        	.andExpect(status().isOk())
	        	.andExpect(view().name("friend"))
	        	.andExpect(model().hasNoErrors())
	        	.andExpect(model().size(1))
				.andReturn();
	}	
}