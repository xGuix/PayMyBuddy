package com.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
	@GetMapping({"/", "homepage"})
	public String homepage()
	{ 
		return "homepage";
	}
	
	@GetMapping(value ="/paid")
	public String paid()
	{
		return "paid";
	}
}