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

/**
 *  Model Object BankAccount
 */
@Entity
@Table(name ="bankaccount")
public class BankAccount implements Serializable
{
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3546751409063987902L;

	/**
	 * Bank account Id DB field
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bankaccount_id")
	private Integer bankaccountId;
	
	/**
	 * Iban account DB field
	 */
	@Column(name="iban_account")
	private String ibanAccount;
	
	/**
	 * Bank name DB field
	 */
	@Column(name="bank_name")
	private String bankName;

	/**
	 *  User to link by Id
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	/**
	 *  Default Constructor
	 */
	public BankAccount()
	{}
	
	/**
	 *  Specific Constructor 
	 *  
	 *  @param ibanAccount String bank number
	 *  @param bankName String bank name
	 *  @param user One user
	 */
	public BankAccount (String ibanAccount, String bankName, User user)
	{
		this.ibanAccount = ibanAccount;
		this.bankName = bankName;
		this.user = user;
	}
	
	/**
	 *  Get serial version uid
	 *  
	 *  @return serialVersionUID
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	/**
	 *  Get bank account Id
	 *  
	 *  @return bankaccountId
	 */
	public Integer getBankaccountId()
	{
		return bankaccountId;
	}

	/**
	 *  Set bank account Id
	 *  
	 *  @param bankaccountId Integer bank account
	 */
	public void setBankaccountId(Integer bankaccountId)
	{
		this.bankaccountId = bankaccountId;
	}

	/**
	 *  Get iban account number
	 *  
	 *  @return ibanAccount
	 */
	public String getIbanAccount() 
	{
		return ibanAccount;
	}

	/**
	 *  Set iban account number
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
	 *  @return bankName
	 */
	public String getBankName()
	{
		return bankName;
	}

	/**
	 *  Set bank name
	 *  
	 *  @param bankName String bank name
	 */
	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	/**
	 *  Get user name of bank account
	 *  
	 *  @return user User bank account
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 *  Set user name
	 *  
	 *  @param user User user
	 */
	public void setUser(User user)
	{
		this.user = user;
	}
}