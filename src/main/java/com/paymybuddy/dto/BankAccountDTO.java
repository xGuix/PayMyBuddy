package com.paymybuddy.dto;

import com.paymybuddy.model.User;

public class BankAccountDTO
{
	private User user;
	private String ibanAccount;
	private String bankName;
	
	public BankAccountDTO()
	{}
	
	public BankAccountDTO(User user, String ibanAccount, String bankName)
	{
		this.user = user;
		this.ibanAccount = ibanAccount;
		this.bankName = bankName;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getIbanAccount()
	{
		return ibanAccount;
	}

	public void setIbanAccount(String ibanAccount)
	{
		this.ibanAccount = ibanAccount;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}
}