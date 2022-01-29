package com.paymybuddy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;

@Service
public class UserService
{
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
	
	public User getUserSave(User user)
	{
		return userRepository.save(user);
	}

	public void deleteUserById(Integer id)
	{
		userRepository.deleteById(id);
	}
}