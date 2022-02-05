package com.paymybuddy.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="transaction")
public class Transaction
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_transaction")
	private int idTransaction;
	
	@Column(name="id_user")
	private int idUser;
	
	@Column(name="user_email")
	private String userEmail;

	@Column(name="date_time")
	private LocalDateTime dateTime;
	
	@Column(name="amount")
	private float amount;
	
	@Column(name="description")
	private String description;
	
	@Column(name="transcation_fee")
	private float transcationFee;

	public Transaction(int idTransaction, int idUser, String userEmail, LocalDateTime dateTime,
			float amount, String description, float transcationFee)
	{
		super();
		this.idTransaction = idTransaction;
		this.idUser = idUser;
		this.userEmail = userEmail;
		this.dateTime = dateTime;
		this.amount = amount;
		this.description = description;
		this.transcationFee = transcationFee;
	}

	public int getIdTransaction()
	{
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction)
	{
		this.idTransaction = idTransaction;
	}

	public int getIdUser()
	{
		return idUser;
	}

	public void setIdUser(int idUser) 
	{
		this.idUser = idUser;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public LocalDateTime getDateTime() 
	{
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount)
	{
		this.amount = amount;
	}

	public String getDescription()
{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public float getTranscationFee()
	{
		return transcationFee;
	}

	public void setTranscationFee(float transcationFee)
	{
		this.transcationFee = transcationFee;
	}

	@Override
	public String toString()
	{
		return "Transaction ["
				+ "idTransaction=" + idTransaction 
				+ ", idUser=" + idUser 
				+ ", userEmail=" + userEmail
				+ ", dateTime=" + dateTime 
				+ ", amount=" + amount 
				+ ", description=" + description 
				+ ", transcationFee=" + transcationFee
				+ "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(amount, dateTime, description, idTransaction, idUser, transcationFee, userEmail);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Transaction other = (Transaction) obj;
		return Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount)
				&& Objects.equals(dateTime, other.dateTime) 
				&& Objects.equals(description, other.description)
				&& idTransaction == other.idTransaction
				&& idUser == other.idUser
				&& Float.floatToIntBits(transcationFee) == Float.floatToIntBits(other.transcationFee)
				&& Objects.equals(userEmail, other.userEmail);
	}	
}