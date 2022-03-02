package com.paymybuddy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@Controller
public class HomeController
{
	private final UserService userService;
	private String homepage = "homepage";

	public HomeController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping({"/", "homepage"})
	public String homepage(Model model)
	{ 
		model.addAttribute("success", "Success!");
		return homepage;
	}
	
	@GetMapping("/profile")
	public String profile(Model model, Principal principal)
	{
		String userEmail = principal.getName();
		User user = userService.getUserByEmail(userEmail);
		if(user!=null)
		{
			model.addAttribute("user", user);
			return homepage;
		}			
		return homepage;
	}
}