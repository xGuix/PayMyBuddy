package com.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SendController
{
	@GetMapping(value ="/send")
	public String send()
	{
		return "send";
	}
}