package com.paymybuddy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PayMyBuddyApplication
{
	private static Logger loggerMain = LogManager.getLogger("PayMyBuddyApplication");
	
	@Bean
	public ModelMapper modelMapper()
	{
	    return new ModelMapper();
	}
	
	public static void main(String[] args)
	{
		SpringApplication.run(PayMyBuddyApplication.class, args);
		loggerMain.info("PayMyBuddy application has started");
	}
}