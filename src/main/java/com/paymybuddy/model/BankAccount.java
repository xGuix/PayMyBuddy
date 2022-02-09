package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="bankaccount")
public class BankAccount
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bankaccount_id")
	private int bankaccountId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="iban_account")
	private String ibanAccount;
	
	@Column(name="bank_name")
	private String bankName;

	
	public BankAccount(int bankaccountId, int userId, String ibanAccount, String bankName)
	{
		this.bankaccountId = bankaccountId;
		this.userId = userId;
		this.ibanAccount = ibanAccount;
		this.bankName = bankName;
	}

	public int getBankaccountId()
	{
		return bankaccountId;
	}

	public void setBankaccountId(int bankaccountId)
	{
		this.bankaccountId = bankaccountId;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
{
		this.userId = userId;
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