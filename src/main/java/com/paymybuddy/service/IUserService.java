package com.paymybuddy.service;

import java.math.BigDecimal;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.model.User;

public interface IUserService extends UserDetailsService
{
	User addUser(SignupDTO signupDto);
	User updateUser(String userEmail, String firstname, String lastname, String city, String email);
	User getUserByEmail(String userEmail);
	
	BigDecimal addMoneyToBalance(User userBalance, BigDecimal balance);
	BigDecimal withdrawMoneyToBank(User userBalance, BigDecimal balance);
	
	String validateUser(SignupDTO user);
	String addToFriends(User user, User friendToAdd);
}