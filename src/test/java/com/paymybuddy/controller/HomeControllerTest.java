package com.paymybuddy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;
import com.paymybuddy.service.IUserService;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest
{		
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	IUserService userService;
		
	@Autowired
	HomeController homeController;
	
	BigDecimal balance = BigDecimal.ZERO;
	List<User> userListTest;
	User userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, userListTest);

    String ibanAccount = "FR111333999222777";
    String bankName = "Banque de France";
	BankAccount bankAccount= new BankAccount(ibanAccount,bankName,userTest);
	
    String homepage ="http://localhost/homepage";
    String redirectLogin = "http://localhost/login";

	@Test
	void getHomepageWhenNotLoginReturnLoginPage() throws Exception
	{	
		mockMvc.perform(get("/homepage"))
	        	.andExpect(status().isFound())
				.andExpect(result -> result.equals(userTest))
				.andExpect(redirectedUrl(redirectLogin));
	}
	
	@Test
	void getHomepageLoginReturnHomepage() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		mockMvc.perform(get("/homepage")
				.param("principal", "gb@paymybuddy.com")
				.param("model", " "))
	        	.andExpect(status().isFound());
	}
}