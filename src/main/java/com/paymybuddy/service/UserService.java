package com.paymybuddy.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.dto.UserDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;

@Service
public class UserService implements IUserService
{
	private static Logger logger = LogManager.getLogger("UserServiceLog");
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepository userRepository;
	  
	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepository.findByEmail(username);
		if(user == null)
		{
			throw new UsernameNotFoundException("Invalid email or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
	}

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
	 * Get user with id :
	 * Find the user with user id
	 * 
	 * @return User The user with id
	 */
	public User getUserById(Integer userId)
	{
		logger.info("User id to find: {}",userId);
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
		logger.info("User email to find: {}",email);
	    return userRepository.findByEmail(email);
	}
	
	/**
	 * Verify existing User :
	 * Check if user email already exists in database
	 * 
	 * @return Message Information string
	 */
	public String validateUser(SignupDTO userDto)
	{
        String message = "Not Found";
        User userToFind = getUserByEmail(userDto.getEmail());
        if(userToFind !=null)
        {
        	message = "Email address already used!";
        }
		return message;
    }
	
	/**
	 * Add a new user :
	 * Create & save user in list
	 * 
	 * @return User The user added
	 */
	public User addUser(SignupDTO signupDto)
	{	
		User checkUser = getUserByEmail(signupDto.getEmail());
		if(checkUser == null)
		{
			User newUser = new User();
			newUser.setFirstname(signupDto.getFirstname());
			newUser.setLastname(signupDto.getLastname());
			newUser.setCity(signupDto.getCity());
			newUser.setBalance(BigDecimal.ZERO);
			newUser.setEmail(signupDto.getEmail());
			newUser.setPassword(bCryptPasswordEncoder.encode(signupDto.getPassword()));
			
			userRepository.saveAndFlush(newUser);
			logger.info("User added and saved");
			return newUser;
		}
		return checkUser;
	}
	
	/**
	 * Update a new user :
	 * Modify & save user in list
	 * 
	 * @return User The user updated
	 */
	public User updateUser(String userEmail, String firstname, String lastname, String city, String email)
	{	
		User userToUpdate = userRepository.findByEmail(userEmail);
		if(userToUpdate.getEmail().equals(userEmail))
		{
			userToUpdate.setFirstname(firstname);
			userToUpdate.setLastname(lastname);
			userToUpdate.setCity(city);
			userToUpdate.setEmail(email);
			userRepository.saveAndFlush(userToUpdate);
		}
		else {
			logger.info("User does not exists!");
		}
		logger.info("User update and saved");		
		return userToUpdate;
	}

	/**
	 * Add a connection between users :
	 * New friend add to friends list
	 * 
	 * @return UserToAdd User friend to add
	 */
	public User addToFriends(User user, User friendToAdd)
	{
		user.addUserFriend(friendToAdd);
		userRepository.saveAndFlush(user);
		logger.info("User added to your friend list");
		return friendToAdd;
	}
	
	/**
	 * Add money to balance :
	 * New cash deposite add to balance
	 * 
	 * @return newBalance Operation on balance
	 */
	public BigDecimal addMoneyToBalance(User user, BigDecimal deposit)
	{
		BigDecimal balance = user.getBalance().add(deposit);	
		user.setBalance(balance);
		userRepository.saveAndFlush(user);
		logger.info("Money deposite to balance");
		return balance;
	}
	
	/**
	 * Withdraw money from balance :
	 * New cash deposite to bank account
	 * 
	 * @return newBalance Operation on balance
	 */
	public BigDecimal withdrawMoneyToBank(User user, BigDecimal withdraw)
	{
		BigDecimal balance = user.getBalance().subtract(withdraw);	
		user.setBalance(balance);
		userRepository.saveAndFlush(user);
		logger.info("Money withdraw from balance");
		return balance;
	}
}