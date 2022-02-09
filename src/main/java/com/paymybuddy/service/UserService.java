package com.paymybuddy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dto.UserDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;

@Service
public class UserService
{
	private static Logger logger = LogManager.getLogger("UserServiceLog");
	
	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private ModelMapper modelMapper;
	   
    public UserDTO entityToDto(User user)
    {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> convertListToDTOList(List<User> friendsList)
    {
        List<UserDTO> userListDto = new ArrayList<>();
        for(User u : friendsList)
        {
        	UserDTO newUserDto = new UserDTO();
        	newUserDto.setUserId(u.getUserId());
        	newUserDto.setFirstname(u.getFirstname());
        	newUserDto.setLastname(u.getLastname());
        	newUserDto.setCity(u.getCity());
        	newUserDto.setEmail(u.getEmail());
        	newUserDto.setBalance(u.getBalance());
        	newUserDto.setConnections(null);
        	
	       	List<UserDTO> friendListDto = new ArrayList<>();
	        	for(User uf : u.getFriendsList())
	        	{ 
//        		UserDTO friendDto = new UserDTO();
//        		friendDto.setUserId(uf.getUserId());
//        		friendDto.setFirstname(uf.getFirstname());
//        		friendDto.setLastname(uf.getLastname());
//        		friendDto.setCity(uf.getCity());
//        		friendDto.setEmail(uf.getEmail());
//        		friendDto.setBalance(uf.getBalance());
        		friendListDto.add(entityToDto(uf));
        	}
        	newUserDto.setConnections(friendListDto);
        	userListDto.add(newUserDto);
        }
        return userListDto;
    }
    
	public List<User> getUsers()
	{
		logger.info("Users list found");	
		return userRepository.findAll();
	}
	
	public User getUserById(Integer id)
	{
		logger.info("User found with id: {}",id);
		return userRepository.getById(id);
	}
	
	public User getUserByEmail(String email)
	{
		logger.info("User found with email: {}",email);
	    return userRepository.findByEmail(email);
	}
	 
	public User addUser(User user)
	{	
		logger.info("User add and saved");		
		return userRepository.save(user);
	}
	
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
			userRepository.save(userToUpdate);
		}
		else {
			logger.info("User does not exists!");
		}
		logger.info("User update and saved");		
		return userToUpdate;
	}
	
	public void deleteUserById(Integer id)
	{
		userRepository.deleteById(id);
		logger.info("User deleted");
	}
}