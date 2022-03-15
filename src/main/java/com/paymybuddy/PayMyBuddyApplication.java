package com.paymybuddy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  PAY MY BUDDY v1.0:
 *  -
 *  Spring boot service
 *  Jpa repositories
 *  Thymeleaf frontend
 *  Bootstrap html5/css3
 * 
 *  @author xGuix
 *  @version v1.0
 */
@SpringBootApplication
@EnableAutoConfiguration
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