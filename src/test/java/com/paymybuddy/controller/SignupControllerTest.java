package com.paymybuddy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.service.AccessUserDetailService;
import com.paymybuddy.service.IUserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SignupController.class)
class SignupControllerTest
{		
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccessUserDetailService accessUserDetailService;
	
	@MockBean
	IUserService userService;

	String err;
	SignupDTO signupTest;
	User userSetup ;
	
	@BeforeEach
	void setup()
	{
		err = "Not Found";
		signupTest = new SignupDTO("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin");
		userSetup = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", null, null);
	}
	
	@Test
	void getSignUpViewReturnSignupPage() throws Exception
	{	
		mockMvc.perform(get("/signup")
				.param("model", "user"))
	        	.andExpect(status().isOk())
	        	.andExpect(view().name("signup"))
	        	.andExpect(model().hasNoErrors())
		    	.andExpect(model().size(1))
		    	.andExpect(model().attributeExists("user"))
		    	.andReturn();
	}
	
	@Test
	void postSignUpWhenReturnNotFound() throws Exception
	{	
		when(userService.validateUser(signupTest)).thenReturn(err);
		
		mockMvc.perform(post("/signup")
				.param("model", "user"))
	        	.andExpect(status().isFound())
				.andReturn();
	}
	
//	@Test
//	void postSignUpWhenReturnError() throws Exception
//	{
//		when(userService.addUser(signupTest)).thenReturn(userSetup);
//
//		mockMvc.perform(post("/signup")
//				.param("model", "user")
//				.param("SignupDTO", "signupTest"))
//	        	.andExpect(status().isFound())
//		    	.andExpect(flash().attributeCount(1))
//		    	.andExpect(model().attributeHasErrors())
//		    	.andExpect(redirectedUrl("/signup"))
//				.andReturn();
//	}
}