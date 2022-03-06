package com.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.dto.SignupDTO;
import com.paymybuddy.service.UserService;

@Controller
public class SignupController
{
	private final UserService userService;

	public SignupController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping(value = "/signup")
	public String signUpView(Model model)
	{
		model.addAttribute("user", new SignupDTO());
		return "signup"; 
	}

	@PostMapping(value = "/signup")
	public String signUp(@Validated SignupDTO user, BindingResult result, Model model, RedirectAttributes redirAttrs)
	{
		String err = userService.validateUser(user);
	    if (!"Not Found".equals(err))
	    {
	        ObjectError error = new ObjectError("globalError", err);
	        result.addError(error);
	    }
	    if (result.hasErrors())
	    {
	    	redirAttrs.addFlashAttribute("error", err);
	        return "redirect:/signup";
	    }
	    redirAttrs.addFlashAttribute("success", "Success!");
	    userService.addUser(user);
	    return "redirect:/login";
	}
}