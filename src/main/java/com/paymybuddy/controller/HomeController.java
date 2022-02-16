package com.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@Controller
public class HomeController
{
	private final UserService userService;
	
	@GetMapping(value = "/login")
	public String loginPage(Model model)
	{
		return "login";
	}

	public HomeController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping(value = "/signup")
	public String signUpView(Model model)
	{
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping(value = "/signup")
	public String signUp(@Validated SignupDTO user, BindingResult result, Model model)
	{
		if (result.hasErrors())
		{
			return "signup";
		}
		try {
			userService.addUser(user);
			return "redirect:/login";
		}
		catch (Exception e) {
			model.addAttribute("errorMsg", "User existed in the database");
		}
		return "signup";
	}
	
	@GetMapping({"/", "homepage"})
	public String homepage()
	{
		return "homepage";
	}
}