package com.paymybuddy.controller;

import java.math.BigDecimal;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;
import com.paymybuddy.service.BankAccountService;
import com.paymybuddy.service.UserService;

@Controller
public class HomeController
{
	private String homepage = "homepage";
	private String redirectHomepage = "redirect:/homepage";

	private UserService userService;
	private BankAccountService bankAccountService;

	public HomeController(UserService userService,
			BankAccountService bankAccountService, BankAccountController bankAccountController)
	{
		this.userService = userService;
		this.bankAccountService = bankAccountService;
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

		model.addAttribute("user", user);
		return homepage; 
	}
	
	@PostMapping("/addbank")
	public String addbank(String ibanaccount, String bankname, Model model,
			Principal principal, RedirectAttributes redirAttrs)
	{
		String userEmail = principal.getName();
		User userBank = userService.getUserByEmail(userEmail);
		BankAccount findAccount = userBank.getBankAccount();
		if(findAccount==null)
		{
			BankAccountDTO newBankAccount = new BankAccountDTO(userBank,bankname,ibanaccount);
			bankAccountService.addBankAccount(newBankAccount);
			redirAttrs.addFlashAttribute("bankaccountAdded", "Success!");
			return redirectHomepage;
		}
		model.addAttribute("bankaccount", findAccount);
		return homepage;
	}
	
	@PostMapping("/addmoney")
	public String addmoney (BigDecimal deposite, Model model, Principal principal)
	{
		String userEmail = principal.getName();
		User userBalance = userService.getUserByEmail(userEmail);
		model.addAttribute("balance", userBalance);
		return redirectHomepage;	
	}
}