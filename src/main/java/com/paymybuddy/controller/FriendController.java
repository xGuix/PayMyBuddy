package com.paymybuddy.controller;

import java.security.Principal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.model.User;
import com.paymybuddy.service.IUserService;

@Controller
public class FriendController
{
	private static Logger logger = LogManager.getLogger("FriendControllerLog");
	
	@Autowired
	private final IUserService userService;
	
	private String friend = "redirect:/friend";

	public FriendController(IUserService userService)
	{
		this.userService = userService;
	}
	
	/**
	 *  Get friends data of user:
	 * 	- Friends list
	 * 
	 * @return friend Friend page url
	 */
	@GetMapping("/friend")
	public String transfer(Model model, Principal principal) 
	{
		String userEmail = principal.getName();
		User userActiv = userService.getUserByEmail(userEmail);
		List<User> friendList = userActiv.getFriendsList();
		model.addAttribute("friend", friendList);
		logger.info("Friend data loaded");
		return "friend";
	}
	
	/**
	 *  Search one friend :
	 * 	Email of user as request parameter
	 * 
	 * @return friend Friend page url
	 */
	@GetMapping("/findFriend")
	public String findFriends(Model model, RedirectAttributes redirAttrs,
			@RequestParam(value = "email") String email, Principal principal)
	{
		User userToFind = userService.getUserByEmail(email);
		if (userToFind == null || email.equalsIgnoreCase(principal.getName()))
		{
			redirAttrs.addFlashAttribute("searchError", "User doesn't exist");
			logger.info("Email address not found: {}", email);
			return friend;
		}
		redirAttrs.addFlashAttribute("searchResult", userToFind);
		logger.info("Email address found: {}", email);
		return friend;
	}
	
	/**
	 *  Add one friend :
	 * 	Email of user as request parameter
	 * 	if found user will be added to your friends list
	 * 
	 * @return friend Friend page url
	 */
	@PostMapping("/friend")
	public String addFriend(Model model, RedirectAttributes redirAttrs, 
			@RequestParam(value = "email") String email, Principal principal)
	{
		User user = userService.getUserByEmail(principal.getName());
		User friendToAdd = userService.getUserByEmail(email);
		if (friendToAdd == null || user.getFriendsList().contains(friendToAdd))
		{
			redirAttrs.addFlashAttribute("addError", "Wrong email, user does not exist or friend already added!");
			logger.info("Error, user not found");
			return friend;
		}
		userService.addToFriends(user, friendToAdd);
		redirAttrs.addFlashAttribute("addFriendSuccess", 
				friendToAdd.getFirstname()+" "+friendToAdd.getLastname()+" have been added to your friends list");
		logger.info("Friend added to you friends list");
		return friend;
	}
}