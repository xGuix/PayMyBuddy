package com.paymybuddy.model;

import java.time.LocalDateTime;

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
	
	@Column(name="id_sender")
	private int idSender;
	
	@Column(name="id_receiver")
	private int idReceiver;

	@Column(name="date_time")
	private LocalDateTime dateTime;
	
	@Column(name="amount")
	private float amount;
	
	@Column(name="description")
	private String description;
	
	@Column(name="transaction_fee")
	private float transcationFee;

	public Transaction(int idTransaction, int idSender, int idReceiver, LocalDateTime dateTime,
			float amount, String description, float transcationFee)
	{
		super();
		this.idTransaction = idTransaction;
		this.idSender = idSender;
		this.idReceiver = idReceiver;
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

	public int getIdSender()
	{
		return idSender;
	}

	public void setIdSender(int idSender) 
	{
		this.idSender = idSender;
	}

	public int getIdReceiver()
	{
		return idReceiver;
	}

	public void setIdReceiver(int idReceiver)
	{
		this.idReceiver = idReceiver;
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
}