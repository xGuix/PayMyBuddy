package com.paymybuddy.integration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.model.User;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="gb@paymybuddy.com")
class UserIntegrationTestIT
{
	@Autowired
	private MockMvc mockMvc;
	
	User userTest;
	BigDecimal balance = BigDecimal.ZERO;
	List<User> userListTest;
	
	@BeforeEach
	void setupTest()
	{
		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, userListTest);
	}
	
	@Test
	void whenCallHomepage() throws Exception
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
	    		.andReturn();
	}
}