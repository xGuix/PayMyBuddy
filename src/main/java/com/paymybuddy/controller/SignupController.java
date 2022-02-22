package com.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@Controller
public class SignupController
{
	private final UserService userService;
	private String signup = "signup";
	
	public SignupController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping(value = "/signup")
	public String signUpView(Model model)
	{
		model.addAttribute("user", new User());
		return signup;
	}

	@PostMapping(value = "/signup" )
	public String signUp(@Validated SignupDTO user, BindingResult result, Model model, RedirectAttributes redirAttrs)
	{
		if (result.hasErrors())
		{
			return signup;
		}
		try {
			redirAttrs.addFlashAttribute("success", "Success!");
			userService.addUser(user);
			return "redirect:/login";
		}
		catch (Exception e) {
			model.addAttribute("errorMsg", "Email address already exists!");
		}
		return signup;
	}
}