package com.paymybuddy.integration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="gb@paymybuddy.com")
class UserIntegrationTestIT
{
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void whenUserCallSignupReturnSignupPage() throws Exception
	{		
	    mockMvc.perform(get("/signup")
				.param("model", "user"))
	        	.andExpect(status().isOk())
	        	.andExpect(view().name("signup"))
	    		.andReturn();
	}
	
	@Test
	void whenLoginPageCallReturnLoginPage() throws Exception
	{
		mockMvc.perform(get("/login"))
	    		.andExpect(view().name("login"))
	    		.andExpect(status().isOk())
	    		.andReturn();
	}
	
	@Test
	void whenLoginPageCallLoginUserDoesNotExists() throws Exception
	{
	    mockMvc.perform(formLogin("/login")
	    		.user("test@test.com")
	    		.password("TestWord"))
	    		.andExpect(status().isFound())
	        	.andExpect(unauthenticated())
	        	.andReturn();
	}
	  
	@Test
	void whenLoginPageCallLoginUserExists() throws Exception
	{
	    mockMvc.perform(formLogin("/login")
	    		.user("gb@paymybuddy.com")
	    		.password("Admin"))
	    		.andExpect(status().isFound())
	    		.andExpect(authenticated())
	    		.andReturn();
	}

	@Test
	void whenCallHomepageReturnFullUserLoad() throws Exception
	{		
	    mockMvc.perform(get("/homepage")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user"))
	        	.andExpect(status().isOk())
	        	.andExpect(view().name("homepage"))
	        	.andExpect(content().string(containsString("Guix")))
	        	.andExpect(content().string(containsString("Debrens")))
	        	.andExpect(content().string(containsString("Orion")))
	        	.andExpect(content().string(containsString("gb@paymybuddy.com")))
	        	.andExpect(content().string(containsString("FR111333999222777")))
	        	.andExpect(content().string(containsString("Banque de France")))
	    		.andReturn();
	}
	
	@Test
	void whenCallAddBankReturnHomepageWithBankAccount() throws Exception
	{	
	    mockMvc.perform(post("/addbank")
				.param("ibanaccount", "FR111333999222777")
				.param("bankname", "Banque de France")
	    		.param("principal", "gb@paymybuddy.com"))
	        	.andExpect(status().isFound())
	        	.andExpect(view().name("redirect:/homepage"))
	    		.andReturn();
	}
	
	@Test
	void whenCallAddMoneyReturnNewBalance() throws Exception
	{	
	    mockMvc.perform(post("/addmoney")
				.param("balance", "100.00"))
	        	.andExpect(status().isFound())
	        	.andExpect(view().name("redirect:/homepage"))
	    		.andReturn();
	}
	
	@Test
	void whenCallWithdrawmoneyReturnNewBalance() throws Exception
	{	
	    mockMvc.perform(post("/withdrawmoney")
				.param("balance", "100.00"))
	        	.andExpect(status().isFound())
	        	.andExpect(view().name("redirect:/homepage"))
	    		.andReturn();
	}
	
	@Test
	void whenSendTransactionToFriend() throws Exception
	{
		mockMvc.perform(get("/send")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user"))
				.andExpect(status().isOk())
				.andExpect(view().name("send"))
				.andReturn();
	}
}