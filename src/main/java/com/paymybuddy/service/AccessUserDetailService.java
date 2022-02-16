package com.paymybuddy.service;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;

@Service
public class AccessUserDetailService implements UserDetailsService
{
	private static Logger logger = LogManager.getLogger("TransactionControllerLog");
	
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
    {
        User user = userRepository.findByEmail(email);

     	if (user == null) {
     		logger.info("User not found: {}",email);
     		throw new UsernameNotFoundException("User " + email + " was not found in the database");
	}

	logger.info("Found User: {}", user);

	return new org.springframework.security.core.userdetails.User(
			user.getEmail(),
			user.getPassword(),
			Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}