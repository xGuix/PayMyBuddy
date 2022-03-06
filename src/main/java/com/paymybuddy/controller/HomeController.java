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

	public HomeController(UserService userService, BankAccountService bankAccountService)
	{
		this.userService = userService;
		this.bankAccountService = bankAccountService;
	}
	
	/**
	 *  Get homepage of user:
	 *  All stuff on profile :
	 *  - Add bank account
	 * 	- Add money to balance
	 *  - Update user info
	 * 
	 * @return homepage Homepage url
	 */
	@GetMapping({"/","/homepage"})
	public String homepage(Model model, Principal principal)
	{
		String userEmail = principal.getName();
		User user = userService.getUserByEmail(userEmail);
		BankAccount findAccount = user.getBankAccount();
		
		model.addAttribute("success", "Success!");
		model.addAttribute("user", user);
		model.addAttribute("bankaccount", findAccount);
		return homepage; 
	}
	
	/**
	 *  Add or update bank account:
	 * 
	 * @return homepage Homepage url
	 */
	@PostMapping("/addbank")
	public String addbank(String ibanaccount, String bankname, Model model,
			Principal principal, RedirectAttributes redirAttrs)
	{
		String userEmail = principal.getName();
		User userBank = userService.getUserByEmail(userEmail);
		BankAccount findAccount = userBank.getBankAccount();
		if(findAccount==null)
		{
			BankAccountDTO newBankAccount = new BankAccountDTO(userBank,ibanaccount,bankname);
			bankAccountService.addBankAccount(newBankAccount);
			redirAttrs.addFlashAttribute("bankaccountAdded", "Success!");
			return redirectHomepage;
		}
		findAccount.setIbanAccount(ibanaccount);
		findAccount.setBankName(bankname);
		bankAccountService.updateBankAccount(userBank.getUserId(), findAccount);
		redirAttrs.addFlashAttribute("bankaccountUpdate", "Update!");
		return redirectHomepage;
	}
	
	/**
	 *  Add money to balance:
	 * 
	 * @return homepage Homepage url
	 */
	@PostMapping("/addmoney")
	public String addmoney (BigDecimal balance, Model model, Principal principal, RedirectAttributes redirAttrs)
	{
		String userEmail = principal.getName();
		User userBalance = userService.getUserByEmail(userEmail);
		userService.addMoneyToBalance(userBalance, balance);
		redirAttrs.addFlashAttribute("balance", userBalance);
		return redirectHomepage;	
	}
	
	/**
	 *  Update user profile:
	 * 
	 * @return homepage Homepage url
	 */
	@PostMapping("/updateuser")
	public String updateProfile (User user, Model model, Principal principal, RedirectAttributes redirAttrs)
	{
		String userEmail = principal.getName();
		userService.updateUser(userEmail, user);
		redirAttrs.addFlashAttribute("userUpdate", "Success!");
		return redirectHomepage;	
	}
}