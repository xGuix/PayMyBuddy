package com.paymybuddy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.dto.UserDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;

@Service
@Transactional
public class UserService
{
	private static Logger logger = LogManager.getLogger("UserServiceLog");
	
	@Autowired
	private UserRepository userRepository;
  
	/**
	 * Convert Entity :
	 * Get userDTO with user
	 * 
	 * @return UsersDTO The user convert in DTO
	 */
    public UserDTO entityToDto(User user)
    {
    	UserDTO newUserDto = new UserDTO();
    	newUserDto.setUserId(user.getUserId());
    	newUserDto.setFirstname(user.getFirstname());
    	newUserDto.setLastname(user.getLastname());
    	newUserDto.setCity(user.getCity());
    	newUserDto.setEmail(user.getEmail());
    	newUserDto.setBalance(user.getBalance());
    	newUserDto.setFriendsList(null);
    	
       	List<UserDTO> friendListDto = new ArrayList<>();
        	for(User uf : user.getFriendsList())
        	{ 
	    		UserDTO friendDto = new UserDTO();
	    		friendDto.setUserId(uf.getUserId());
	    		friendDto.setFirstname(uf.getFirstname());
	    		friendDto.setLastname(uf.getLastname());
	    		friendDto.setCity(uf.getCity());
	    		friendDto.setEmail(uf.getEmail());
	    		friendDto.setBalance(uf.getBalance());
	    		friendDto.setFriendsList(null);
	    		friendListDto.add(friendDto);
        	}
        newUserDto.setFriendsList(friendListDto);       
        return newUserDto;
    }
    
	/**
	 * Convert List of Entity :
	 * Get userDTO list with user list
	 * 
	 * @return List<UserDTO> The list of user convert in DTO list
	 */
    public List<UserDTO> convertListToDTOList(List<User> friendsList)
    {
        List<UserDTO> userListDto = new ArrayList<>();
        for(User u : friendsList)
        {  
        	userListDto.add(entityToDto(u));
        }
        return userListDto;
    }
    
	/**
	 * Get list of users :
	 * Find all full users list
	 * 
	 * @return List<User> The list of all full users
	 */
	public List<User> getUsers()
	{
		logger.info("Users list found");	
		return userRepository.findAll();
	}
	
	/**
	 * Get user with id :
	 * Find the user with user id
	 * 
	 * @return User The user with id
	 */
	public User getUserById(Integer userId)
	{
		logger.info("User found with id: {}",userId);
		return userRepository.getById(userId);
	}
	
	/**
	 * Get user with email :
	 * Find the user with user email
	 * 
	 * @return User The user with email
	 */
	public User getUserByEmail(String email)
	{
		logger.info("User found with email: {}",email);
	    return userRepository.findByEmail(email);
	}
	
	/**
	 * Add a new user :
	 * Create & save user in list
	 * 
	 * @return User The user added
	 */
	public User addUser(User user)
	{	
		logger.info("User add and saved");		
		return userRepository.saveAndFlush(user);
	}
	
	/**
	 * Update a new user :
	 * Modify & save user in list
	 * 
	 * @return User The user updated
	 */
	public User updateUser(String email, User user)
	{	
		User userToUpdate = userRepository.findByEmail(email);
		if(userToUpdate.getEmail().equals(email))
		{
			userToUpdate.setFirstname(user.getFirstname());
			userToUpdate.setLastname(user.getLastname());
			userToUpdate.setCity(user.getCity());
			userToUpdate.setBalance(user.getBalance());
			userToUpdate.setPassword(user.getPassword());
			userToUpdate.setFriendsList(user.getFriendsList());
			userRepository.saveAndFlush(userToUpdate);
		}
		else {
			logger.info("User does not exists!");
		}
		logger.info("User update and saved");		
		return userToUpdate;
	}
	
	/**
	 * Delete user from list :
	 * Delete a user with id
	 */
	public void deleteUserById(Integer userId)
	{
		userRepository.deleteById(userId);
		logger.info("User deleted");
	}
}