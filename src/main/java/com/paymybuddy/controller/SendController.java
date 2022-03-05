package com.paymybuddy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.paymybuddy.model.User;
import com.paymybuddy.service.TransactionService;
import com.paymybuddy.service.UserService;

@Controller
public class SendController
{
	private UserService userService;
	private TransactionService transactionService;
	//private String send = "redirect:/send";

	public SendController(UserService userService, TransactionService transactionService)
	{
		this.userService = userService;
		this.transactionService = transactionService;
	}
	@GetMapping(value ="/send")
	public String send(Model model, Principal principal)
	{
		String userEmail = principal.getName();
		User userToFind = userService.getUserByEmail(userEmail);
		Integer userId = userToFind.getUserId();
		transactionService.getTransactionsForUser(userId);
		return "send";
	}
}