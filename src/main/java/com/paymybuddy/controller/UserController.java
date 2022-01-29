package com.paymybuddy.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.dto.UserDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@RestController
public class UserController
{
	private static Logger logger = LogManager.getLogger("ControllerLogger");
	
	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	@RolesAllowed("USER")
	public Iterable<User> getUsers()
	{
		logger.info("Get users list");
		return userService.getUsers();
	}
	
	@RequestMapping("/user")
	@RolesAllowed("USER")
	public Optional<User> getUserById(int id)
	{
		logger.info("Get user with id={}",id);		
		Optional<User> optUser = userService.getUserById(id);
		
		if(optUser.isPresent())
		{	
			User userId = optUser.get();
			userId.getUserFriends().forEach(User::getEmail);
		}
		return optUser;
	}	
	
	@GetMapping("/userEmail")
	@RolesAllowed("USER")
	public Optional<User> getUserByEmail(String email)
	{
		logger.info("Get user with email={}",email);
		return userService.getUserByEmail(email);
	}
	
	@PostMapping("/addUser")
	@RolesAllowed("USER")
	public User addUser(@RequestBody UserDTO userDto)
	{
		logger.info("Get users list");
		return userService.addUser(userDto);
	}
	
	@DeleteMapping("/deleteUser")
	@RolesAllowed("USER")
	public void deleteUser(int id)
	{
		logger.info("DeleteUser");
		userService.deleteUserById(id);
	}
	
	/***********************************
	@RequestMapping("/admin")
	@RolesAllowed("ADMIN")
	public String getAdmin()
	{
		return "Welcome to you: Admin";
	}
	***********************************/	
}