package com.paymybuddy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
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

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.service.AccessUserDetailService;
import com.paymybuddy.service.ITransactionService;
import com.paymybuddy.service.IUserService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(SendController.class)
@WithMockUser(username="gb@paymybuddy.com")
class SendControllerTest
{		
	@Autowired
	private MockMvc mockMvc;
		
	@MockBean
	private AccessUserDetailService accessUserDetailService;
	
	@MockBean
	private IUserService userService;
	
	@MockBean
	private ITransactionService transactionService;
			
	User userTest;
	User userFriend;
	BigDecimal balance;
	List<User> friendList;
	List<Transaction> transactionSent;
	List<Transaction> transactionReceiver;

	@BeforeEach
	void setup()
	{
		balance = BigDecimal.valueOf(100.00);
		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, friendList);
		userFriend = new User("Bob","Lazar","Groomlake","bl@zone51.com", "Zone51", balance, friendList);
	}
	
	@Test
	void getSendReturnSendpageWithAllInfo() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		
		mockMvc.perform(get("/send")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user"))
	        	.andExpect(status().isOk())
	        	.andExpect(view().name("send"))
	        	.andExpect(model().hasNoErrors())
	        	.andExpect(model().size(4))
	        	.andExpect(model().attributeExists("user"))
	        	.andExpect(model().attribute("friend",friendList))
	        	.andExpect(model().attributeExists("transactions"))
	        	.andExpect(model().attributeExists("transactionsReceiver"))
				.andReturn();
	}
	
	@Test
	void postSendMoneyReturnSendpageWithNewTransaction() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		
		mockMvc.perform(post("/sendmoney")
				.param("email", "bl@zone51.com")
				.param("message", "It's a gift test")
				.param("amount", "10.00")
				.param("model", "user")
				.param("principal", "gb@paymybuddy.com"))
	        	.andExpect(status().isFound())
	        	.andExpect(view().name("redirect:/send"))
	        	.andExpect(model().hasNoErrors())
	        	.andExpect(flash().attributeCount(1))
	        	.andExpect(flash().attribute("transactionSuccess", "Success!"))
				.andReturn();
	}
	
	@Test
	void postSendMoneyReturnSendpageWithNegativeAmount() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		
		mockMvc.perform(post("/sendmoney")
				.param("email", "bl@zone51.com")
				.param("message", "It's a gift test")
				.param("amount", "-100.00")
				.param("model", "user")
				.param("principal", "gb@paymybuddy.com"))
	        	.andExpect(status().isFound())
	        	.andExpect(view().name("redirect:/send"))
	        	.andExpect(flash().attributeCount(1))
	        	.andExpect(flash().attribute("errorNegative", "Negative amount error"))
				.andReturn();
	}
}