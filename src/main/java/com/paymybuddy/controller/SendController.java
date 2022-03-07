package com.paymybuddy.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.service.IUserService;
import com.paymybuddy.service.TransactionService;

@Controller
public class SendController
{
	private IUserService userService;
	private TransactionService transactionService;

	public SendController(IUserService userService, TransactionService transactionService)
	{
		this.userService = userService;
		this.transactionService = transactionService;
	}
	
	@GetMapping("/send")
	public String send(Model model, Principal principal)
	{
		String userEmail = principal.getName();
		User userActiv = userService.getUserByEmail(userEmail);
		List<Transaction> transactions = transactionService.getTransactionsByUserId();
		
		model.addAttribute("transactions", transactions);
		model.addAttribute("user", userActiv);
		return "send";
	}
}