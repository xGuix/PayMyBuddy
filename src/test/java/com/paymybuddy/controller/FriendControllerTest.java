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

import com.paymybuddy.model.User;
import com.paymybuddy.service.AccessUserDetailService;
import com.paymybuddy.service.IUserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FriendController.class)
@WithMockUser(username="gb@paymybuddy.com")
class FriendControllerTest
{		
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccessUserDetailService accessUserDetailService;
	
	@MockBean
	private IUserService userService;
			
	User userTest;
	User userFriend;
	BigDecimal balance;
	List<User> friendList;

	@BeforeEach
	void setup()
	{
		balance = BigDecimal.valueOf(100.00);
		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, friendList);
		userFriend = new User("Bob","Lazar","Groomlake","bl@zone51.com", "Zone51",balance, friendList);
	}

	@Test
	void getTransferReturnFriendpageWithFriendInfo() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		mockMvc.perform(get("/friend")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user"))
	        	.andExpect(status().isOk())
	        	.andExpect(view().name("friend"))
	        	.andExpect(model().size(1))
	        	.andExpect(model().attribute("friend",friendList))
				.andReturn();
	}

	@Test
	void getFindFriendsReturnFriendPageWithNewFriend() throws Exception
	{	
		when(userService.getUserByEmail(userFriend.getEmail())).thenReturn(userFriend);
		mockMvc.perform(get("/findFriend")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user")
				.param("email","bl@zone51.com"))
	        	.andExpect(status().isFound())
	        	.andExpect(view().name("redirect:/friend"))
	        	.andExpect(flash().attribute("searchResult",userFriend))
				.andReturn();
	}
	
	@Test
	void getFindFriendsReturnNull() throws Exception
	{	
		when(userService.getUserByEmail(userFriend.getEmail())).thenReturn(null);
		mockMvc.perform(get("/findFriend")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user")
				.param("email","bl@zone51.com"))
	        	.andExpect(status().isFound())
	        	.andExpect(view().name("redirect:/friend"))
	        	.andExpect(flash().attributeCount(1))
	        	.andReturn();
	}
	
	@Test
	void getFindFriendsReturnFriendPageWithSearchError() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		mockMvc.perform(get("/findFriend")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user")
				.param("email","gb@paymybuddy.com"))
	        	.andExpect(status().isFound())
	        	.andExpect(view().name("redirect:/friend"))
	        	.andExpect(flash().attributeCount(1))
	        	.andReturn();
	}
	
	@Test
	void postAddFriendReturnFriendPageWithNewFriend() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);

		mockMvc.perform(post("/friend")
				.param("model", "user")
				.param("principal", "gb@paymybuddy.com")
				.param("email","bl@zone51.com"))
	        	.andExpect(status().isFound())
	        	.andExpect(view().name("redirect:/friend"))
	        	.andExpect(flash().attributeCount(1))
	        	.andReturn();
	}
	
//	@Test
//	void postAddFriendReturnNull() throws Exception
//	{	
//		mockMvc.perform(post("/friend")
//				.param("principal", "gb@paymybuddy.com")
//				.param("model", "user")
//				.param("email","bl@zone51.com"))
//	        	.andExpect(status().isFound())
//	        	.andExpect(view().name("redirect:/friend"))
//	        	.andExpect(flash().attributeCount(1))
//	        	.andReturn();
//	}
}