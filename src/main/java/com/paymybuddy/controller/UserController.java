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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
		logger.info("Get user with id= {}",id);	
		
		return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);
	}	
	
	/**
	 * Get one user by email
	 * 
	 * @return User A user with email
	 */
	@GetMapping(value = "/userEmail")
	@RolesAllowed("USER")
	public ResponseEntity<User> getUserByEmail(String email)
	{
		logger.info("Get user with email= {}",email);	
		
		return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.FOUND);
	}
	
	/**
	 * Add one user to list
	 * 
	 * @return User user saved
	 */
	@PostMapping(value = "/user")
	@RolesAllowed("USER")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto)
	{	
		UserDTO userToAdd = userService.entityToDto(userService.addUser(userDto));
		
		logger.info("User added is: {}",userDto);	
		return new ResponseEntity<>(userToAdd, HttpStatus.CREATED);
	}
	
	/**
	 * Update one user of list
	 * 
	 * @return User user saved
	 */
	@PutMapping(value = "/user")
	@RolesAllowed("USER")
	public ResponseEntity<UserDTO> updateUser(@RequestParam String email, @RequestBody UserDTO userDto)
	{	
		UserDTO userToUpdate = null;
		if(getUserByEmail(email)!=null)
		{
			userToUpdate = userService.entityToDto(userService.updateUser(email, userDto));
		}
		else {
			logger.info("User does not exists: {}", userToUpdate);	
		}
		logger.info("User updated: {}",userToUpdate);	
		return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
	}
	
	/**
	 * Delete one user from list
	 * 
	 * @return User User deleted 
	 */
	@DeleteMapping(value = "/user")
	@RolesAllowed("USER")
	public ResponseEntity<String> deleteUser(int id)
	{
		String info = null;
		Optional<User> userDel = userService.getUserById(id);
		if (userDel.isPresent())
		{
			User toDel = userDel.get();
			info = toDel.getFirstname()+" "+toDel.getLastname();
			
			userService.deleteUserById(id);
			logger.info("User Deleted:{}",info);
		}
		else {
			logger.info("User does not exists! Result is: {}", info);
		}
		return new ResponseEntity<>("User deleted is "+ info, HttpStatus.OK);
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