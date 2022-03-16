package com.paymybuddy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.service.AccessUserDetailService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
@WithMockUser(username="NoLog")
class LoginControllerTest
{		
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccessUserDetailService accessUserDetailService;
	
	@Test
	void getLoginReturnLoginPage() throws Exception
	{	
		mockMvc.perform(get("/login"))
	        	.andExpect(status().isOk())
	        	.andExpect(view().name("login"))
	        	.andExpect(model().hasNoErrors())
	        	.andExpect(model().size(0))
	        	.andReturn();
	}
	
	@Test
	void getAccessDeniedReturnError() throws Exception
	{	
		mockMvc.perform(get("/error"))
	        	.andExpect(status().isOk())
	        	.andExpect(view().name("error"))
	        	.andExpect(model().hasNoErrors())
	        	.andExpect(model().size(1))
	        	.andExpect(model().attributeExists("message"))
	        	.andReturn();
	}
}