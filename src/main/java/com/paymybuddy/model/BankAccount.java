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
	@Column(name="id_bankaccount")
	private int idBankaccount;
	
	@Column(name="id_user")
	private int idUser;
	
	@Column(name="iban_account")
	private String ibanAccount;
	
	@Column(name="bank_name")
	private String bankName;

	
	public BankAccount(int idBankaccount, int idUser, String ibanAccount, String bankName)
	{
		this.idBankaccount = idBankaccount;
		this.idUser = idUser;
		this.ibanAccount = ibanAccount;
		this.bankName = bankName;
	}

	public int getIdBankaccount()
	{
		return idBankaccount;
	}

	public void setIdBankaccount(int idBankaccount)
	{
		this.idBankaccount = idBankaccount;
	}

	public int getIdUser()
	{
		return idUser;
	}

	public void setIdUser(int idUser)
{
		this.idUser = idUser;
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