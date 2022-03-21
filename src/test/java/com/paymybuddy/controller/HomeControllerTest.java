package com.paymybuddy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;
import com.paymybuddy.service.AccessUserDetailService;
import com.paymybuddy.service.IBankAccountService;
import com.paymybuddy.service.ITransactionService;
import com.paymybuddy.service.IUserService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeController.class)
@WithMockUser(username="gb@paymybuddy.com")
class HomeControllerTest
{		
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccessUserDetailService accessUserDetailService;
	
	@MockBean
	private IUserService userService;

	@MockBean
	private IBankAccountService bankAccountService;
	
	@MockBean
	private ITransactionService transactionService;
			
	BigDecimal balance;
	User userTest;
	User userUpdateEmail;
	User userUpdateWithoutEmail;
	
	String firstnameTest;
	String lastnameTest;
	String cityTest;
	String emailTest;
	
    String ibanAccount;
    String bankName;
	BankAccount bankAccount;

	@BeforeEach
	void setup()
	{
		balance = BigDecimal.valueOf(100.00);
		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, null);
		
	    ibanAccount = "FR111333999222777";
	    bankName = "Banque de France";
		bankAccount= new BankAccount(ibanAccount,bankName,userTest);
		
		firstnameTest = "firstname";
		lastnameTest = "lastname";
		cityTest = "city";
		emailTest = "email@test.com";
		
		userUpdateEmail = new User(firstnameTest,lastnameTest,cityTest,emailTest,"Admin",balance,null);
		userUpdateWithoutEmail = new User(firstnameTest,lastnameTest,cityTest,"gb@paymybuddy.com","Admin",balance,null);

	}

	@Test
	void getHomepageWhenNotBankAccountReturnHomepageWithInfoNoBankAccount() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		mockMvc.perform(get("/homepage")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user"))
	        	.andExpect(status().isOk())
	        	.andExpect(view().name("homepage"))
	        	.andExpect(model().hasNoErrors())
	        	.andExpect(model().size(2))
	        	.andExpect(model().attributeExists("success"))
	        	.andExpect(model().attributeExists("user"))
				.andReturn();
	}
	
	@Test
	void getHomepageLoginReturnHomepage() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		userTest.setBankAccount(bankAccount);
		
		mockMvc.perform(get("/homepage")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user"))
	        	.andExpect(status().isOk())
	        	.andExpect(view().name("homepage"))
		    	.andExpect(model().hasNoErrors())
		    	.andExpect(model().size(3))
	        	.andExpect(model().attributeExists("success"))
		    	.andExpect(model().attributeExists("user"))
		    	.andExpect(model().attributeExists("bankaccount"))
				.andReturn();
	}
	
	@Test
	void postAddbankReturnRedirectUpdateHomepageWithNoBankAccount() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		
		mockMvc.perform(post("/addbank")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user"))
	        	.andExpect(status().isFound())
		    	.andExpect(model().hasNoErrors())
		    	.andExpect(flash().attributeCount(1))
		    	.andExpect(flash().attribute("bankaccountAdded", "Success!"))
				.andExpect(redirectedUrl("/homepage"))
				.andReturn();
	}
	
	@Test
	void postAddbankReturnRedirectUpdateHomepageWithBankAccount() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		userTest.setBankAccount(bankAccount);
		
		mockMvc.perform(post("/addbank")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user"))
	        	.andExpect(status().isFound())
		    	.andExpect(model().hasNoErrors())
		    	.andExpect(flash().attributeCount(1))
		    	.andExpect(flash().attribute("bankaccountUpdate", "Update!"))
		    	.andExpect(redirectedUrl("/homepage"))
				.andReturn();
	}
	
	@Test
	void postAddmoneyReturnRedirectUpdateHomepageWithNegativeBalance() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		
		mockMvc.perform(post("/addmoney")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user")
				.param("balance", "-100.00"))
	        	.andExpect(status().isFound())
		    	.andExpect(model().hasNoErrors())
		    	.andExpect(flash().attributeCount(1))
		    	.andExpect(flash().attribute("errorNegative", "You cannot deposite negative amount!"))
		    	.andExpect(redirectedUrl("/homepage"))
				.andReturn();
	}
	
	@Test
	void postAddmoneyReturnRedirectUpdateHomepageWithPositiveBalance() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		
		mockMvc.perform(post("/addmoney")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user")
				.param("balance", "100.00"))
	        	.andExpect(status().isFound())
		    	.andExpect(model().hasNoErrors())
		    	.andExpect(flash().attributeCount(1))
		    	.andExpect(flash().attribute("balance", userTest))
		    	.andExpect(redirectedUrl("/homepage"))
				.andReturn();
	}
	
	@Test
	void postWithdrawmoneyReturnRedirectUpdateHomepageWithNegativeBalance() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		
		mockMvc.perform(post("/withdrawmoney")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user")
				.param("balance", "-100.00"))
	        	.andExpect(status().isFound())
		    	.andExpect(model().hasNoErrors())
		    	.andExpect(flash().attributeCount(1))
		    	.andExpect(flash().attribute("errorNegative", "You cannot withdraw negative amount!"))
		    	.andExpect(redirectedUrl("/homepage"))
				.andReturn();
	}
	
	@Test
	void postWithdrawmoneyReturnRedirectUpdateHomepageWithPositiveBalance() throws Exception
	{	
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		
		mockMvc.perform(post("/withdrawmoney")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user")
				.param("balance", "100.00"))
	        	.andExpect(status().isFound())
		    	.andExpect(model().hasNoErrors())
		    	.andExpect(flash().attributeCount(1))
		    	.andExpect(flash().attribute("balance", userTest))
		    	.andExpect(redirectedUrl("/homepage"))
				.andReturn();
	}
	
	@Test
	void postUpdateprofileReturnRedirectUpdateProfileHomepageWithNewInfos() throws Exception
	{	
		when(userService.updateUser(userTest.getEmail(),firstnameTest,lastnameTest,cityTest,"gb@paymybuddy.com")).thenReturn(userUpdateWithoutEmail);
		
		mockMvc.perform(post("/updateprofile")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user")
				.param("firstname", "firstname")
				.param("lastname", "lastname")
				.param("city", "city")
				.param("email", "gb@paymybuddy.com"))
	        	.andExpect(status().isFound())
		    	.andExpect(model().hasNoErrors())
		    	.andExpect(flash().attributeCount(1))
		    	.andExpect(flash().attribute("userUpdate", "Success!"))
		    	.andExpect(redirectedUrl("/homepage"))
				.andReturn();
	}
	
	@Test
	void postUpdateprofileReturnRedirectLoginPageWithEmailUpdate() throws Exception
	{	
		when(userService.updateUser(userTest.getEmail(),firstnameTest,lastnameTest,cityTest,emailTest)).thenReturn(userUpdateEmail);
		
		mockMvc.perform(post("/updateprofile")
				.param("principal", "gb@paymybuddy.com")
				.param("model", "user")
				.param("firstname", "firstname")
				.param("lastname", "lastname")
				.param("city", "city")
				.param("email", "email@Test.com"))
	        	.andExpect(status().isFound())
		    	.andExpect(model().hasNoErrors())
		    	.andExpect(flash().attributeCount(1))
		    	.andExpect(flash().attribute("success", "Success!"))
		    	.andExpect(redirectedUrl("/login"))
				.andReturn();
	}
}