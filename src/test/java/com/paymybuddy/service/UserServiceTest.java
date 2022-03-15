package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import org.springframework.boot.test.mock.mockito.MockBean;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;

/**
 * Test Class for user service
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest 
{
	@MockBean
	AccessUserDetailService accessUserDetailService;
	
    @InjectMocks
    UserService userService;
    
    @Mock
    UserRepository userRepository;
   	
	User userTest;
	User userFriendTest;
	Integer userId = 1;
	Integer userFriendId = 2;
	BigDecimal balance = BigDecimal.ZERO;
	SignupDTO signupDto;
	User notExists;
	List<User> userListTest;
    
	@BeforeEach
	void setupTest()
	{
		userListTest = new ArrayList<>();
		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, userListTest);
		userFriendTest = new User("Bob","Lazar","Groomlake","bl@paymybuddy.com", "Zone51", balance, userListTest);
		signupDto = new SignupDTO("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin");
		notExists = new User("TestFirstname", "TestLastname","TestCity", "test@test.com","PasswordTest",balance, userListTest);
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
	void TestIfAddUserReturnNull()
	{
		when(userService.getUserByEmail(userTest.getEmail())).thenReturn(null);
		
		User userToTest = userService.addUser(signupDto);
		
		assertEquals(userTest.getFirstname(), userToTest.getFirstname());
		assertEquals(userTest.getLastname(), userToTest.getLastname());
		assertEquals(userTest.getCity(), userToTest.getCity());
		assertEquals(userTest.getBalance(), userToTest.getBalance());
		assertEquals(userTest.getEmail(), userToTest.getEmail());
		verify(userRepository, Mockito.times(1)).saveAndFlush(userToTest);
	}
	
	@Test
	void TestIfupdateUserReturnUser()
	{
		when(userRepository.findByEmail(userTest.getEmail())).thenReturn(userTest);
		
		User userToTest = userService.updateUser(
				userTest.getEmail(),
				userTest.getFirstname(), userTest.getLastname(),
				userTest.getCity(), userTest.getEmail());
		
		assertEquals(userTest, userToTest);
		verify(userRepository, Mockito.times(1)).findByEmail(userTest.getEmail());
	}
	
	@Test
	void TestIfupdateUserReturnNotExists()
	{
		when(userRepository.findByEmail(userTest.getEmail())).thenReturn(notExists);
		
		User userToTest = userService.updateUser(userTest.getEmail(),
				"TestFirstname", "TestLastname", "TestCity", "test@test.com");
		
		assertNotEquals(userTest.getEmail(), userToTest.getEmail());
		verify(userRepository, Mockito.times(1)).findByEmail(userTest.getEmail());
	}

	@Test
	void TestIfAddFriendReturnNewUser()
	{
		when(userRepository.saveAndFlush(userTest)).thenReturn(userTest);
		
		User userToTest = userService.addToFriends(userTest, userFriendTest);
		
		assertEquals(userTest.getFriendsList(), userToTest.getFriendsList());
		verify(userRepository, Mockito.times(1)).saveAndFlush(userTest);
	}
	
	@Test
	void TestIfAddMoneyToBalanceReturnBigDecimal()
	{
		when(userRepository.saveAndFlush(userTest)).thenReturn(userTest);
		
		BigDecimal userBalance = userService.addMoneyToBalance(userTest, balance);
		
		assertEquals(userTest.getBalance(), userBalance);
		verify(userRepository, Mockito.times(1)).saveAndFlush(userTest);
	}
	
	@Test
	void TestIfWithdrawMoneyToBalanceReturnBigDecimal()
	{
		when(userRepository.saveAndFlush(userTest)).thenReturn(userTest);
		
		BigDecimal userBalance = userService.withdrawMoneyToBank(userTest, balance);
		
		assertEquals(userTest.getBalance(), userBalance);
		verify(userRepository, Mockito.times(1)).saveAndFlush(userTest);
	}
}