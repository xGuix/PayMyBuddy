package com.paymybuddy.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@RestController
public class UserController
{
	private static Logger logger = LogManager.getLogger("ControllerLogger");
	
	@Autowired
	private UserService userService;

	/**
	 * Read All list :
	 * Get all users
	 * 
	 * @return UsersList Full users list
	 */
	@GetMapping(value = "/users")
	@RolesAllowed("USER")
	public ResponseEntity<Iterable<User>> getUsers()
	{
		logger.info("Get users list");		
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.FOUND);
	}
	
	/**
	 * Get one user by id
	 * 
	 * @return User A user with id
	 */
	@GetMapping(value = "/user")
	@RolesAllowed("USER")
	public ResponseEntity<Optional<User>> getUserById(int id)
	{
		Optional<User> optUser = userService.getUserById(id);		
		if(optUser.isPresent())
		{	
			User userId = optUser.get();
			userId.getUserFriends().forEach(User::getEmail);
		}
		logger.info("Get user with id={}",id);		
		return new ResponseEntity<>(optUser, HttpStatus.FOUND);
	}	
	
	/**
	 * Get one user by email
	 * 
	 * @return User A user with email
	 */
	@GetMapping(value = "/userEmail")
	@RolesAllowed("USER")
	public ResponseEntity<Optional<User>> getUserByEmail(String email)
	{
		logger.info("Get user with email={}",email);		
		return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.FOUND);
	}
	
	/**
	 * Add  or update one user to list
	 * 
	 * @return User user saved
	 */
	@PostMapping(value = "/user")
	@RolesAllowed("USER")
	public ResponseEntity<User> saveUser(@RequestBody User user)
	{	
		userService.saveUser(user);
		logger.info("User added");	
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	/**
	 * Delete one user from list
	 * 
	 * @return User User deleted 
	 */
	@DeleteMapping(value = "/user")
	@RolesAllowed("USER")
	public ResponseEntity<Optional<User>> deleteUser(int id)
	{
		Optional<User> userDel = userService.getUserById(id);
		userService.deleteUserById(id);
		logger.info("DeleteUser");
		return new ResponseEntity<>(userDel,HttpStatus.OK);
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