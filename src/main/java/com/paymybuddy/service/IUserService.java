package com.paymybuddy.service;

import java.math.BigDecimal;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.model.User;

/**
 * Interface for user service
 */
public interface IUserService extends UserDetailsService
{
	/**
	 * {@inheritDoc}
	 */
	User addUser(SignupDTO signupDto);
	
	/**
	 * {@inheritDoc}
	 */
	User updateUser(String userEmail, String firstname, String lastname, String city, String email);
	
	/**
	 * {@inheritDoc}
	 */
	User getUserByEmail(String userEmail);
	
	/**
	 * {@inheritDoc}
	 */
	User addToFriends(User user, User friendToAdd);
	
	/**
	 * {@inheritDoc}
	 */
	BigDecimal addMoneyToBalance(User userBalance, BigDecimal balance);
	
	/**
	 * {@inheritDoc}
	 */
	BigDecimal withdrawMoneyToBank(User userBalance, BigDecimal balance);
	
	/**
	 * {@inheritDoc}
	 */
	String validateUser(SignupDTO user);
}