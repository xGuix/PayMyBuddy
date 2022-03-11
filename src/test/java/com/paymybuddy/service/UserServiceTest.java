package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest 
{
    @InjectMocks
    UserService userService;
    
    @Mock
    UserRepository userRepository;
   
	User userTest;
	User userFriendTest;
	Integer userId = 1;
	Integer userFriendId = 2;
	BigDecimal balance = BigDecimal.ZERO;
	List<User> userListTest;
	SignupDTO signupDto;
    
	@BeforeEach
	void setupTest()
	{
		userListTest = new ArrayList<>();
		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, userListTest);
		userFriendTest = new User("Bob","Lazar","Groomlake","bl@paymybuddy.com", "Zone51", balance, userListTest);
		signupDto = new SignupDTO("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin");
	}
 
	@Test
	void TestIfGetUserByIdReturnUser()
	{
		when(userRepository.getById(1)).thenReturn(userTest);
		
		User userToTest = userService.getUserById(userId);
		
		assertEquals(userTest, userToTest);
		verify(userRepository, Mockito.times(1)).getById(userId);
	}
	
	@Test
	void TestIfGetUserByEmailReturnUser()
	{
		when(userRepository.findByEmail(userTest.getEmail())).thenReturn(userTest);
		
		User userToTest = userService.getUserByEmail(userTest.getEmail());
		
		assertEquals(userTest, userToTest);
		verify(userRepository, Mockito.times(1)).findByEmail(userTest.getEmail());
	}
	
	@Test
	void TestIfValidateUserReturnNotFoundString()
	{
		String stringTest = "Not Found";
		String stringUser = userService.validateUser(signupDto);
		
		assertEquals(stringTest,stringUser);
	}
	
	@Test
	void TestIfValidateUserReturnAlreadyUsedString()
	{
		when(userRepository.findByEmail(userTest.getEmail())).thenReturn(userTest);
		String stringTest = "Email address already used!";
		String stringUser = userService.validateUser(signupDto);
		
		assertEquals(stringTest,stringUser);
		verify(userRepository, Mockito.times(1)).findByEmail(userTest.getEmail());
	}
		
	@Test
	void TestIfAddUserReturnNewUser()
	{
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(userTest);
		
		User userToTest = userService.addUser(signupDto);
		
		assertEquals(userTest, userToTest);
	}
	
	@Test
	void TestIfupdateUserReturnUser()
	{
		when(userRepository.findByEmail(userTest.getEmail())).thenReturn(userTest);
		
		User userToTest = userService.updateUser(
				userTest.getEmail(),
				userTest.getFirstname(),userTest.getLastname(),
				userTest.getCity(), userTest.getEmail());
		
		assertEquals(userTest, userToTest);
		verify(userRepository, Mockito.times(1)).findByEmail(userTest.getEmail());
	}
}