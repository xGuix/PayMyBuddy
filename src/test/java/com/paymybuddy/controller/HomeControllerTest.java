package com.paymybuddy.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = HomeController.class)
class HomeControllerTest
{
//	@Autowired
//	MockMvc mockMvc;
//	
//	@MockBean
//	UserService userService;
//
//	User userTest;
//	BigDecimal balance = BigDecimal.ZERO;
//	List<User> userListTest;
//
//	@BeforeEach
//	void setupTest()
//	{
//		userTest = new User("Guix","Debrens","Orion","gb@paymybuddy.com", "Admin", balance, userListTest);
//	}
//	
//	@Test
//	void whenCallHomepage() throws Exception
//	{	
//		when(userService.getUserByEmail("gb@paymybuddy.com"))
//				.then(RETURNS_DEFAULTS);
//		
//	    mockMvc.perform(get("/homepage")
//				.param("email", "gb@paymybuddy.com"))
//	        	.andExpect(status().isOk())
//	    		.andReturn();
//	}
}