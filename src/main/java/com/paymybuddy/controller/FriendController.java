package com.paymybuddy.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@Controller
public class FriendController
{
	private final UserService userService;
	private String user = "User";
	private String friend = "friend";

	public FriendController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping(value ="/friend")
	public String transfer(Model model, Principal principal) 
	{
		String userEmail = principal.getName();
		User userToFind = userService.getUserByEmail(userEmail);
		List<User> friendList = userToFind.getFriendsList();
		model.addAttribute("friend", friendList);
		return friend;
	}
	
	@GetMapping("/findFriends")
	public String findFriends(Model model, @RequestParam(value = "email") String email, Principal principal)
	{
		User userToFind = userService.getUserByEmail(email);
		if (email.equalsIgnoreCase(principal.getName()))
		{
			model.addAttribute("searchError", "User doesn't exist");
		}
		else {
			model.addAttribute("searchResult", userToFind);
		}
		model.addAttribute("friends", userService.getUserByEmail(principal.getName()).getFriendsList());
		
		return friend;
	}
	
	@GetMapping("/addFriend")
	public String addToContact(Model model, @RequestParam(value = "email") String email, Principal principal)
	{
		User fromUser = userService.getUserByEmail(principal.getName());
		User toUser = userService.getUserByEmail(email);
		userService.addToContact(fromUser, toUser);
		model.addAttribute("addToContactMsgSuc", user + email + " have been added to your contact");
		model.addAttribute("friends", userService.getUserByEmail(principal.getName()).getFriendsList());
		return friend;
	}
}