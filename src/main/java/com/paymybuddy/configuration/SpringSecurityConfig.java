package com.paymybuddy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	
	//@Autowired
	//UserDetailService userDetailSerice
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
			.withUser("BobLazar").password(passwordEncoder().encode("Zone51"))
			.roles("USER")
			.and()
			.withUser("xGuix").password(passwordEncoder().encode("Admin"))
			.roles("ADMIN","USER");
		
		//auth.userDetailsService(userDetailSerice).passwordEncoder(passwordEncore().Encode("?"))
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/")
			.permitAll()
			.and()
			.csrf().disable();
		
		//http.authorizeRequests()
		//	.antMatchers("/").hasRole("ADMIN")
		//	.antMatchers("/*").hasRole("USER")
		//	.anyRequest().authenticated()
		//	.and()
		//	.formLogin()
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}