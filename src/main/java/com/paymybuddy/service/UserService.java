package com.paymybuddy.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;

@Service
public class UserService
{
	private static Logger logger = LogManager.getLogger("UserServiceLog");
	
	@Autowired
	private UserRepository userRepository;
	   
	public Iterable<User> getUsers()
	{
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(Integer id)
	{
		return userRepository.findById(id);
	}
	
	public Optional<User> getUserByEmail(String email)
	{
	    return userRepository.findByEmail(email);
	}
	 
	public User saveUser(User user)
	{	
		logger.info("User saved");		
		return userRepository.save(user);
	}
    
	public void deleteUserById(int id)
	{
		userRepository.deleteById(id);
		logger.info("User deleted");
	}
}