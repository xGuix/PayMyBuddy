package com.paymybuddy.controller;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController
{
	private static Logger logger = LogManager.getLogger("LoginControllerLog");
	
	/**
	 *  Get login Page:
	 * 
	 * @return login Login page url
	 */
	@GetMapping(value = "/login")
	public String loginPage()
	{
		logger.info("Login page loaded");	
		return "login";
	}
	
	/**
	 *  Error denied access:
	 * 
	 * @return error Error page url
	 */
	@GetMapping(value = "/error")
	public String accessDenied(Model model, Principal principal)
	{
		String message = "Sorry " + principal.getName()	
				+ "<br> You do not have permission to access this page!";
		model.addAttribute("message", message);
		logger.info("Error with user access");	
		return "error";
	}
}