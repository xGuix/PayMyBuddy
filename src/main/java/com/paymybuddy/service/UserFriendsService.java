//package com.paymybuddy.service;
//
//import java.util.Optional;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.paymybuddy.model.User;
//import com.paymybuddy.model.UserFriends;
//import com.paymybuddy.repository.UserFriendsRepository;
//
//@Service
//public class UserFriendsService
//{
//	private static Logger logger = LogManager.getLogger("UserFriendsServiceLog");
//	
//	@Autowired
//	private UserFriendsRepository userFriendsRepository;
//	    
//	public Iterable<User> getFriends()
//	{
//		logger.info("Users list found");	
//		return userFriendsRepository.findAll();
//	}
//	
//	public Optional<User> getFriendById(Integer id)
//	{
//		logger.info("User found with id: {}",id);
//		return userFriendsRepository.findById(id);
//	}
//	
//	public UserFriends addFriend(Integer id)
//	{
//		//Optional<User> newFriend = getFriendById(id);
//		UserFriends addConnection = new UserFriends();
//		addConnection.setUser(id);
//		addConnection.setFriend(null);
//		logger.info("Connection add and saved");		
//		return userFriendsRepository.save(addConnection);
//	}
//		
//	public void deleteFriendById(Integer id)
//	{
//		userFriendsRepository.deleteById(id);
//		logger.info("User deleted");
//	}
//}