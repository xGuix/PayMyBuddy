package com.paymybuddy.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.model.User;

public interface IUserService extends UserDetailsService
{
	User addUser(SignupDTO signupDto);
}