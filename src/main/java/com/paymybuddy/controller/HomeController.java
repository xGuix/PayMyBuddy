package com.paymybuddy.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;
import com.paymybuddy.service.IBankAccountService;
import com.paymybuddy.service.IUserService;

/**
 *  Homepage Thymeleaf controller
 */
@Controller
public class HomeController
{
	private static Logger logger = LogManager.getLogger("HomeControllerLog");
	
	private String homepage = "homepage";
	private String redirectHomepage = "redirect:/homepage";
	private String success = "success";
	private String successString = "Success!";
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBankAccountService bankAccountService;
	
	/**
	 *  Specific constructor
	 * 
	 * 	@param userService User service
	 *  @param bankAccountService Bank account Service
	 */
	public HomeController(IUserService userService, IBankAccountService bankAccountService)
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
	 * @param model Model
	 * @param principal Principal
	 * 
	 * @return homepage Homepage url
	 */
	@GetMapping({"/","/homepage"})
	public String homepage(Model model, Principal principal)
	{
		String userEmail = principal.getName();
		User userActiv = userService.getUserByEmail(userEmail);
		BankAccount findAccount = userActiv.getBankAccount();
		if(findAccount==null)
		{
			model.addAttribute(success, successString);
			model.addAttribute("user", userActiv);
			logger.info("No bank account recorded");
			return homepage; 
		}
		model.addAttribute(success, successString);
		model.addAttribute("user", userActiv);
		model.addAttribute("bankaccount", findAccount);
		logger.info("Full user loaded");
		return homepage; 
	}
	
	/**
	 *  Add or update bank account:
	 * 
	 * @param ibanaccount String
	 * @param bankname String
	 * @param model Model
	 * @param principal Principal
	 * @param redirAttrs RedirectAttributes
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
			redirAttrs.addFlashAttribute("bankaccountAdded", successString);
			logger.info("New bank account saved");
			return redirectHomepage;
		}
		findAccount.setIbanAccount(ibanaccount);
		findAccount.setBankName(bankname);
		bankAccountService.updateBankAccount(userBank.getBankAccount().getBankaccountId(), findAccount);
		redirAttrs.addFlashAttribute("bankaccountUpdate", "Update!");
		logger.info("Bank account updated & saved");
		return redirectHomepage;
	}
	
	/**
	 *  Add money to balance:
	 * 
	 * @param balance BigDecimal
	 * @param model Model
	 * @param principal Principal
	 * @param redirAttrs RedirectAttributes
	 * 
	 * @return homepage Homepage url
	 */
	@PostMapping("/addmoney") 
	public String addmoney(BigDecimal balance, Model model,
			Principal principal, RedirectAttributes redirAttrs)
	{
		String userEmail = principal.getName();
		User userBalance = userService.getUserByEmail(userEmail);
		
		if(balance.compareTo(BigDecimal.ZERO) <= 0)
		{
			redirAttrs.addFlashAttribute("errorNegative", "You cannot deposite negative amount!");
			logger.info("Negative amount not allowed");
			return redirectHomepage;
		}
		userService.addMoneyToBalance(userBalance, balance);
		redirAttrs.addFlashAttribute("balance", userBalance);
		logger.info("Money add to balance");
		return redirectHomepage;	
	}
	
	/**
	 *  Withdraw money to bank:
	 * 
	 * @param balance BigDecimal
	 * @param model Model
	 * @param principal Principal
	 * @param redirAttrs RedirectAttributes
	 * 
	 * @return homepage Homepage url
	 */
	@PostMapping("/withdrawmoney")
	public String withdrawmoney(BigDecimal balance, Model model,
			Principal principal, RedirectAttributes redirAttrs)
	{
		String userEmail = principal.getName();
		User userBalance = userService.getUserByEmail(userEmail);
		
		if(balance.compareTo(BigDecimal.ZERO) <= 0)
		{
			redirAttrs.addFlashAttribute("errorNegative", "You cannot withdraw negative amount!");
			logger.info("Negative amount not allowed");
			return redirectHomepage;
		}
		
		userService.withdrawMoneyToBank(userBalance, balance);
		redirAttrs.addFlashAttribute("balance", userBalance);
		logger.info("Money withdraw from balance");
		return redirectHomepage;	
	}
	
	/**
	 * Update user profile:
	 * 
	 * @param firstname String
	 * @param lastname String
	 * @param city String
	 * @param email String
	 * @param model Model
	 * @param principal Principal
	 * @param redirAttrs RedirectAttributes
	 * 
	 * @return homepage Homepage url
	 */
	@PostMapping("/updateprofile")
	public String updateprofile(String firstname,String lastname, String city, String email, Model model, 
			Principal principal, RedirectAttributes redirAttrs)
	{
		String userEmail = principal.getName();
		userService.updateUser(userEmail, firstname,lastname,city, email);
		
		if (!Objects.equals(userEmail, email))
		{
			redirAttrs.addFlashAttribute(success, successString);
			logger.info("user email successfully update");
			return "redirect:/login";
		}	
		User userUpdate = userService.getUserByEmail(email);
		model.addAttribute("user", userUpdate);
		redirAttrs.addFlashAttribute("userUpdate", successString);
		logger.info("Profile info update & saved");
		return redirectHomepage;	
	}
}