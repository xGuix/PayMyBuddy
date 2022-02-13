package com.paymybuddy.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
public class BankAccount implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3546751409063987902L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bankaccount_id")
	private Integer bankaccountId;
	
	@Column(name="iban_account")
	private String ibanAccount;
	
	@Column(name="bank_name")
	private String bankName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	public BankAccount()
	{}
	
	public BankAccount(Integer bankaccountId, String ibanAccount, String bankName, User user )
	{
		this.bankaccountId = bankaccountId;
		this.ibanAccount = ibanAccount;
		this.bankName = bankName;
		this.user = user;
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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}