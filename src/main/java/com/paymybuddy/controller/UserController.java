package com.paymybuddy.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		logger.info("Get user with id {}",id);
		return userService.getUserById(id);
	}	
	
	
	@RequestMapping("/admin")
	@RolesAllowed("ADMIN")
	public String getAdmin()
	{
		return "Welcome to you: Admin";
	}		
}