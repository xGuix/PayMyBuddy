package com.paymybuddy.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="transaction")
public class Transaction implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1855462102432419372L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private Integer transactionId;
	
	@Column(name="sender")
	private String sender;
	
	@Column(name="receiver")
	private String receiver;

	@Column(name="datetime")
	private LocalDateTime dateTime;
	
	@Column(name="amount")
	private float amount;
	
	@Column(name="description")
	private String description;
	
	@Column(name="transactionfee")
	private float transcationFee;

	public Transaction()
	{}
	
	public Transaction(Integer transactionId, String sender, String receiver,
			LocalDateTime dateTime,	float amount, String description, float transcationFee)
	{
		super();
		this.transactionId = transactionId;
		this.sender = sender;
		this.receiver = receiver;
		this.dateTime = dateTime;
		this.amount = amount;
		this.description = description;
		this.transcationFee = transcationFee;
	}
	
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public Integer getTransactionId()
	{
		return transactionId;
	}

	public void setTransactionId(Integer transactionId)
	{
		this.transactionId = transactionId;
	}

	public String getSender()
	{
		return sender;
	}

	public void setSender(String sender) 
	{
		this.sender = sender;
	}

	public String getReceiver()
	{
		return receiver;
	}

	public void setReceiver(String receiver)
	{
		this.receiver = receiver;
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