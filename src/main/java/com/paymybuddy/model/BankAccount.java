package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="bankaccount")
public class BankAccount
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bankaccount_id")
	private Integer bankaccountId;
	
	@Column(name="iban_account")
	private String ibanAccount;
	
	@Column(name="bank_name")
	private String bankName;
	
	@OneToOne
	@JoinColumn(name="user_email")
	private User email;

	public BankAccount()
	{}
	
	public BankAccount(Integer bankaccountId, String ibanAccount, String bankName )
	{
		this.bankaccountId = bankaccountId;
		this.ibanAccount = ibanAccount;
		this.bankName = bankName;
	}

	public Integer getBankaccountId()
	{
		return bankaccountId;
	}

	public void setBankaccountId(Integer bankaccountId)
	{
		this.bankaccountId = bankaccountId;
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

	public User getUserEmail()
	{
		return email;
	}

	public void setUserEmail(User email)
	{
		this.email = email;
	}
}