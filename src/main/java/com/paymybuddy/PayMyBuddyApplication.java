package com.paymybuddy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.paymybuddy.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class PayMyBuddyApplication
{
	private static Logger loggerMain = LogManager.getLogger("PayMyBuddyApplication");
	
	public static void main(String[] args)
	{
		SpringApplication.run(PayMyBuddyApplication.class, args);
		loggerMain.info("PayMyBuddy application has started");
	}
}