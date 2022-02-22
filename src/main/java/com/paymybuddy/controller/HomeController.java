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
	
//	private final UserService userService;
//	
//	@GetMapping(value = "/login")
//	public String loginPage(Model model)
//	{
//		return "login";
//	}
//
//	public HomeController(UserService userService)
//	{
//		this.userService = userService;
//	}
//
//	@ModelAttribute("user")
//    public SignupDTO userRegistrationDto(Model model) 
//	{
//        return new SignupDTO();
//    }
//		
//	@GetMapping(value = "/signup")
//	public String signUpView(Model model)
//	{
//		model.addAttribute("user", new User());
//		return "signup";
//	}
//
//	@PostMapping(value = "/signup" )
//	public String signUp(@Validated SignupDTO user, BindingResult result, Model model, RedirectAttributes redirAttrs)
//	{
//		if (result.hasErrors())
//		{
//			return "signup";
//		}
//		try {
//			redirAttrs.addFlashAttribute("success", "Success!");
//			userService.addUser(user);
//			return "redirect:/login";
//		}
//		catch (Exception e) {
//			model.addAttribute("errorMsg", "Email address already exists!");
//		}
//		return "signup";
//	}
//	
//	@GetMapping(value ="/friend")
//	public String friend()
//	{
//		return "friend";
//	}
//	
}