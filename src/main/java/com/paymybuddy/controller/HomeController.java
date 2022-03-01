package com.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
//	private final UserService userService;
//	
//	public HomeController(UserService userService)
//	{
//		this.userService = userService;
//	}
//	
	@GetMapping({"/", "homepage"})
	public String homepage(Model model)
	{ 
		model.addAttribute("success", "Success!");
		//userService.updateUser("user", user);
		return "homepage";
	}
	
	@GetMapping(value ="/paid")
	public String paid()
	{
		return "paid";
	}
}