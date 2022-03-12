package com.paymybuddy.dto;

import com.paymybuddy.model.User;

import groovy.transform.Generated;

/**
 *  BankAccount DTO Model Object
 */
@Generated
public class BankAccountDTO
{
	private User user;
	private String ibanAccount;
	private String bankName;
	
	/**
	 *  Default Constructor 
	 */
	public BankAccountDTO()
	{}
	
	/**
	 *  Specific Constructor 
	 *  
	 *  @param user One user
	 *  @param ibanAccount String bank number
	 *  @param bankName String bank name
	 */
	public BankAccountDTO(User user, String ibanAccount, String bankName)
	{
		this.user = user;
		this.ibanAccount = ibanAccount;
		this.bankName = bankName;
	}

	/**
	 *  Get user
	 *  
	 *  @return user User user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 *  Set user
	 *  
	 *  @param user User user
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 *  Get iban bank account
	 *  
	 *  @return ibanAccount String iban Account
	 */
	public String getIbanAccount()
	{
		return ibanAccount;
	}

	/**
	 *  Set iban account
	 *  
	 *  @param ibanAccount String iban account
	 */
	public void setIbanAccount(String ibanAccount)
	{
		this.ibanAccount = ibanAccount;
	}

	/**
	 *  Get bank name
	 *  
	 *  @return bankName String bank name
	 */
	public String getBankName()
	{
		return bankName;
	}

	/**
	 *  Set bank account
	 *  
	 *  @param bankName String bank name
	 */
	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}
}