package com.paymybuddy.integration;

import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTestIT
{
	@Autowired
	MockMvc mockMvc;
		
	@MockBean
	UserService userService;

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
		when(userService.getUserByEmail("gb@paymybuddy.com")).then(RETURNS_DEFAULTS);
		
	    mockMvc.perform(get("/homepage")
				.param("principal", "gb@paymybuddy.com"))
	        	.andExpect(status().isFound())
	    		.andReturn();
	}
}