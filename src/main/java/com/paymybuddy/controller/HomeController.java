package com.paymybuddy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.model.BankAccount;
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
	
	@GetMapping("/")
	public String home(Model model)
	{ 
		model.addAttribute("success", "Success!");
		return homepage;
	}
	
	@GetMapping("/homepage")
	public String homepage(Model model, Principal principal)
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
	
	@PostMapping("/userbank")
	public String bankaccount(Model model, Principal principal, @RequestBody BankAccountDTO bankaccountDto)
	{
		String userEmail = principal.getName();
		User userBank = userService.getUserByEmail(userEmail);
		BankAccount findAccount = userBank.getBankAccount();
		if(findAccount!=null)
		{
			userBank.setBankAccount(findAccount);
			model.addAttribute("bank", findAccount);
			return homepage;
		}
		return homepage;
	}
}