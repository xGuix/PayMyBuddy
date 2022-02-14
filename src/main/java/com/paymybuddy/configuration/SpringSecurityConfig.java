package com.paymybuddy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.paymybuddy.service.AccessUserDetailService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{	
	@Autowired
	AccessUserDetailService userDetailService;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
	
	@Override
	public void configure(HttpSecurity http) throws Exception
	{
//		http.authorizeRequests()
//			.antMatchers("/")
//			.permitAll()
//			.and()
//			.csrf().disable()
//			.formLogin()
//			//.loginPage("/login")
//			.defaultSuccessUrl("/users")
//			.failureUrl("/login?error=true")
//			.usernameParameter("email")
//			.passwordParameter("password")
//			.and()
//			.logout()
//			.logoutUrl("/logout")
//			.logoutSuccessUrl("/login?logout=true");	
		
        http.csrf().disable()
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()
        	.authorizeRequests()
        	.antMatchers("/*login*", "/*signup*").permitAll()
        	.anyRequest().authenticated();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}