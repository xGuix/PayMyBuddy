package com.paymybuddy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.paymybuddy.repository.UserRepository;

import groovy.transform.Generated;

/**
 *  PAY MY BUDDY v1.0:
 *  -
 *  Spring boot service
 *  Jpa repositories
 * 
 *  @author xGuix
 *  @version v1.0
 */
@Generated
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class PayMyBuddyApplication
{
	private static Logger loggerMain = LogManager.getLogger("PayMyBuddyApplication");
	
	/**
	 *  Starting app
	 *  
	 *  @param args String args
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(PayMyBuddyApplication.class, args);
		loggerMain.info("PayMyBuddy application has started");
	}
}