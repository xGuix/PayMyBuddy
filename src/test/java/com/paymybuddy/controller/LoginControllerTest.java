package com.paymybuddy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest
{		
	@Autowired
	MockMvc mockMvc;

	Model model;
	Principal principal;
	
	@Test
	void getLoginReturnLoginPage() throws Exception
	{	
		mockMvc.perform(get("/login"))
	        	.andExpect(status().isOk());
	}
	
	@Test
	void getAccessDeniedReturnError() throws Exception
	{	
		mockMvc.perform(get("/error"))
	        	.andExpect(status().isFound());
	}
}