package com.paymybuddy.controller;

import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@WebMvcTest(controllers = HomeController.class)
class HomeControllerTest
{
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;

	static User user;

	@BeforeEach
	void setupTest() {
		user = new User();
	}
	
	@Test
	void whenCallFirestationAlert() throws Exception {
		
		when(userService.getUserByEmail("gb@paymybuddy.com"))
				.then(RETURNS_DEFAULTS);
		
	    mockMvc.perform(get("/homepage")
	    		.contentType(MediaType.APPLICATION_JSON)
				.param("email", "gb@paymybuddy.com"))
	        	.andExpect(status().isOk())
	    		.andReturn();
	}
}