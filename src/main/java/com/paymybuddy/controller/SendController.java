package com.paymybuddy.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.exception.YourBalanceIsNotEnough;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.service.IUserService;
import com.paymybuddy.service.TransactionService;

@Controller
public class SendController
{
	private IUserService userService;
	private TransactionService transactionService;
	private String send = "send";
	private String sendRedirect = "redirect:/send";

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
		List<User> friendList = userActiv.getFriendsList();
		List<Transaction> transactions = transactionService.getTransactiondsBySender(userActiv);
		
		model.addAttribute("user", userActiv);
		model.addAttribute("friend",friendList);
		model.addAttribute("transactions", transactions);
		return send;
	}
	
	@PostMapping("/sendmoney")
	public String sendmoney(String email, String message, BigDecimal amount, Model model, Principal principal, RedirectAttributes redirAttrs) throws YourBalanceIsNotEnough
	{
		String userEmail = principal.getName();
		User userActiv = userService.getUserByEmail(userEmail);
		
		if(amount.compareTo(BigDecimal.ZERO) <= 1) 
		{
			redirAttrs.addFlashAttribute("errorNegative", "You cannot deposite negative amount!");
			return sendRedirect;
		}
		
		transactionService.sendMoney(userActiv, email, message, amount);
		redirAttrs.addFlashAttribute("transactionSuccess", "Looks all good");
		return sendRedirect;
	}
}