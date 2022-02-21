package com.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@Controller
public class HomeController
{
	private final UserService userService;
	
	@GetMapping(value = "/login")
	public String loginPage(Model model)
	{
		return "login";
	}

	public HomeController(UserService userService)
	{
		this.userService = userService;
	}

	@ModelAttribute("user")
    public SignupDTO userRegistrationDto(Model model) 
	{
        return new SignupDTO();
    }
		
	@GetMapping(value = "/signup")
	public String signUpView(Model model)
	{
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping(value = "/signup" )
	public String signUp(@Validated SignupDTO user, BindingResult result, Model model)
	{
		if (result.hasErrors())
		{
			return "signup";
		}
		try {
			model.addAttribute("success", "Sign up succeed !");
			userService.addUser(user);
			return "redirect:/login?success";
		}
		catch (Exception e) {
			model.addAttribute("errorMsg", "Email address already exists !");
		}
		return "signup";
	}
	
	@GetMapping({"/", "homepage"})
	public String homepage()
	{
		return "homepage";
	}
	
	@GetMapping(value ="/friend")
	public String friend()
	{
		return "friend";
	}
	
	@GetMapping(value ="/paid")
	public String paid()
	{
		return "paid";
	}
}