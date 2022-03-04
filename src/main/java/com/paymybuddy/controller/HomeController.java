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
	private String homepage = "homepage";
	
	private final UserService userService;
	//private final BankAccountService bankAccountService;
	//private final TransactionService transactionService;

	public HomeController(UserService userService /*, BankAccountService bankAccountService, TransactionService transactionService*/)
	{
		this.userService = userService;
		//this.bankAccountService = bankAccountService;
		//this.transactionService = transactionService;
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