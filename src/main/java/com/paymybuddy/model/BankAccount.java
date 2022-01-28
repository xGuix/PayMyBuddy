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
	
	@Column(name="cardnumber")
	private int cardNumber;
	
	@Column(name="bank")
	private String bank;

	public int getIdBankaccount() {
		return idBankaccount;
	}

	public void setIdBankaccount(int idBankaccount) {
		this.idBankaccount = idBankaccount;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
}