package com.paymybuddy.controller;

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

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.dto.UserDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@RestController
public class UserController
{
	private static Logger logger = LogManager.getLogger("UserControllerLogger");
	
	@Autowired
	private UserService userService;
	
	/**
	 * Get one user by id
	 * 
	 * @return User The user with id
	 */
	@GetMapping(value = "/user")
	public ResponseEntity<UserDTO> getUserById(Integer userId)
	{
		UserDTO userById = userService.entityToDto(userService.getUserById(userId));
		
		logger.info("Get user with id= {}",userId);	
		return new ResponseEntity<>(userById, HttpStatus.FOUND);
	}	
	
	/**
	 * Get one user by email
	 * 
	 * @return User The user with email
	 */
	@GetMapping(value = "/userEmail")
	public ResponseEntity<UserDTO> getUserByEmail(String email)
	{
		UserDTO userToFind = userService.entityToDto(userService.getUserByEmail(email));
		
		logger.info("Get user with email= {}",email);			
		return new ResponseEntity<>(userToFind, HttpStatus.FOUND);
	}

	/**
	 * Add one user to list
	 * 
	 * @return User User saved
	 */
	@PostMapping(value = "/user")
	public ResponseEntity<UserDTO> addUser(@RequestBody SignupDTO signupDto)
	{	
		UserDTO userToAdd = null;
		if(userService.getUserByEmail(signupDto.getEmail())==null)
		{
			userToAdd = userService.entityToDto(userService.addUser(signupDto));
			logger.info("User added is: {}",userToAdd);	
		}
		else {
			logger.info("User already exists: {}",userToAdd);	
		}
		return new ResponseEntity<>(userToAdd, HttpStatus.CREATED);
	}
	
	/**
	 * Delete one user from list
	 * 
	 * @return User User deleted 
	 */
	@DeleteMapping(value = "/user")
	public ResponseEntity<String> deleteUser(Integer id)
	{
		String info = null;
		User userToDel = userService.getUserById(id);
		if (userToDel != null)
		{
			info = userToDel.getFirstname()+" "+userToDel.getLastname();		
			userService.deleteUserById(id);		
			logger.info("User: {} have been deleted ", info);
		}
		else {
			logger.info("User does not exists! Result is: {}", info);
		}
		return new ResponseEntity<>(info + ": User have been deleted", HttpStatus.OK);
	}
}