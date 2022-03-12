package com.paymybuddy.controller;

import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.configuration.SpringSecurityConfig;
import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;
import com.paymybuddy.service.BankAccountService;
import com.paymybuddy.service.UserService;

@WebMvcTest(controllers = HomeController.class)
class HomeControllerTest
{
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@MockBean
	BankAccountService bankAccountService;
	
	@MockBean
	SpringSecurityConfig springSecurityConfig;

	User userTest;
	BigDecimal balance = BigDecimal.ZERO;
	List<User> userListTest;
	BankAccount bankAccount;
    String ibanAccount = "FR111333999222777";
    String bankName = "Banque de France";
	
	@BeforeEach
	void setupTest(SpringSecurityConfig springSecurityConfig)
	{
		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, userListTest);
		bankAccount = new BankAccount(ibanAccount,bankName,userTest);
	}
	
	@Test
	void whenCallHomepage() throws Exception
	{	
		when(userService.getUserByEmail("gb@paymybuddy.com")).then(RETURNS_DEFAULTS);
		
	    mockMvc.perform(get("/homepage")
				.param("email", "gb@paymybuddy.com"))
	        	.andExpect(status().isFound())
	    		.andReturn();
	}
}