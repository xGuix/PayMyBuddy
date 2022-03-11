package com.paymybuddy.service;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;

/**
 * UserService class
 */
@Service
public class UserService implements IUserService
{
	private static Logger logger = LogManager.getLogger("UserServiceLog");
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Constructor user service
	 * 
	 * @param userRepository User repository
	 */
	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	/**
	 * Load User details
	 * 
	 * @return UserDetails User details 
	 */
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
	 * Get user with id :
	 * Find the user with user id
	 * 
	 * @param userId Integer Id of user
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
	 * Create and save user in list
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
	 * Modify and save user in list
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