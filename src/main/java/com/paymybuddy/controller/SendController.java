package com.paymybuddy.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.exception.BalanceNotEnough;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.service.IUserService;
import com.paymybuddy.service.TransactionService;

@Controller
public class SendController
{
	private static Logger logger = LogManager.getLogger("SendControllerLog");
	
	private IUserService userService;
	private TransactionService transactionService;
	
	private String send = "send";
	private String sendRedirect = "redirect:/send";

	public SendController(IUserService userService, TransactionService transactionService)
	{
		this.userService = userService;
		this.transactionService = transactionService;
	}
	
	/**
	 *  Get send data of user:
	 *  All stuff on profile :
	 *  - User parametres
	 * 	- Friends list
	 *  - Transactions list
	 * 
	 * @return send Send page url
	 */
	@GetMapping("/send")
	public String send(Model model, Principal principal)
	{
		String userEmail = principal.getName();
		User userActiv = userService.getUserByEmail(userEmail);
		List<User> friendList = userActiv.getFriendsList();
		List<Transaction> transactionsSent = transactionService.getTransactiondsBySender(userActiv);
		List<Transaction> transactionsReceiver = transactionService.getTransactiondsByReceiver(userActiv);
		
		model.addAttribute("user", userActiv);
		model.addAttribute("friend",friendList);
		model.addAttribute("transactions", transactionsSent);
		model.addAttribute("transactionsReceiver", transactionsReceiver);
		logger.info("User data upload: {}",userActiv);
		return send;
	}
	
	/**
	 *  Send money to friends:
	 * 	Allow transactions between buddies
	 * 
	 * @return Redirect Send page url
	 */
	@PostMapping("/sendmoney")
	public String sendmoney(String email, String message, BigDecimal amount,
			Model model, Principal principal, RedirectAttributes redirAttrs) throws BalanceNotEnough
	{
		String userEmail = principal.getName();
		User userActiv = userService.getUserByEmail(userEmail);
		
		if(amount.compareTo(BigDecimal.ZERO) < 1)
		{
			redirAttrs.addFlashAttribute("errorNegative", "Negative amount error");
			return sendRedirect;
		}
		
		transactionService.sendMoney(userActiv, email, message, amount);
		redirAttrs.addFlashAttribute("transactionSuccess", "Success!");
		logger.info("User transaction sent to: {}", email);
		return sendRedirect;
	}
}