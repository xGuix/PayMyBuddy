package com.paymybuddy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
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
	
    @Autowired
    private ModelMapper modelMapper;
	   
    public UserDTO entityToDto(User user)
    {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> convertListToDTOList(List<User> userList)
    {
        List<UserDTO> userListDto = new ArrayList<>();
        for(User u : userList)
        {
        	userListDto.add(entityToDto(u));
        }
        return userListDto;
    }
    
	public Iterable<User> getUsers()
	{
		logger.info("Users list found");	
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(Integer id)
	{
		logger.info("User found with id: {}",id);
		return userRepository.findById(id);
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
			userToUpdate.setUsersList(user.getUsersList());
			userRepository.save(userToUpdate);
		}
		else {
			logger.info("User does not exists!");
		}
		logger.info("User update and saved");		
		return userToUpdate;
	}
	
	public void deleteUserById(int id)
	{
		userRepository.deleteById(id);
		logger.info("User deleted");
	}
}