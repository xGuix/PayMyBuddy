package com.paymybuddy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController
{
	@GetMapping(value = "/login")
	public String loginPage(Model model)
	{
		return "login";
	}
	
	@GetMapping(value = "/error")
	public String accessDenied(Model model, Principal principal)
	{
		String message = "Sorry " + principal.getName()	
				+ "<br> You do not have permission to access this page!";
		model.addAttribute("message", message);
		return "error";
	}
}