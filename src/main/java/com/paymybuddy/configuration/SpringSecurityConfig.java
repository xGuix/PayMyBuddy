package com.paymybuddy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.paymybuddy.service.AccessUserDetailService;

/**
 *  SpringSecurityConfig Class
 *  Configure and enable spring boot Web security
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{	
	@Autowired
	AccessUserDetailService userDetailService;
	
	/**
	 *  Password Encoder
	 *  
	 *  @return BCryptPasswordEncoder User password encoded
 	 */
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	/**
	 *  Configure authentification
 	 */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
	
	/**
	 *  Configure permissions
 	 */
	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
			.authorizeRequests().antMatchers("/signup**", "/js/**", "/css/**", "/img/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").permitAll()
			.and()
			.logout()
			.invalidateHttpSession(true).clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll();		
	}
}