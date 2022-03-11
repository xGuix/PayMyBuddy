package com.paymybuddy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *  Model Object Transaction
 */
@Entity
@Table(name ="transaction")
public class Transaction implements Serializable
{
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1855462102432419372L;

	/**
	 * Transaction id DB field
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private Integer transactionId;
	
	/**
	 * User sender DB field
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="sender_id", nullable=false)
	private User sender;
	
	/**
	 * User receiver DB field
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="receiver_id", nullable=false)
	private User receiver;

	/**
	 * Transaction date and time DB field
	 */
	@Column(name="datetime")
	private LocalDateTime dateTime;
	
	/**
	 * Transaction amount DB field
	 */
	@Column(name="amount")
	private BigDecimal amount;
	
	/**
	 * Transaction description DB field
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * Transaction fee DB field
	 */
	@Column(name="transactionfee")
	private BigDecimal transactionFee;

	/**
	 *  Default Constructor
	 */
	public Transaction()
	{}
	
	/**
	 *  Specific Constructor 
	 *  
	 *  @param sender User sender
	 *  @param receiver User receiver
	 *  @param dateTime LocalDateTime date and time
	 *  @param amount BigDecimal amount of transaction
	 *  @param description String description messafe
	 *  @param transactionFee BigDecimal fee of transaction
	 */
	public Transaction(User sender, User receiver, LocalDateTime dateTime,
			BigDecimal amount, String description, BigDecimal transactionFee)
	{
		this.sender = sender;
		this.receiver = receiver;
		this.dateTime = dateTime;
		this.amount = amount;
		this.description = description;
		this.transactionFee = transactionFee;
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
	 *  Get transaction Id
	 *  
	 *  @return transactionId
	 */
	public Integer getTransactionId()
	{
		return transactionId;
	}

	/**
	 * Set transaction id
	 * 
	 * @param transactionId Integer transaction Id
	 */
	public void setTransactionId(Integer transactionId)
	{
		this.transactionId = transactionId;
	}

	/**
	 *  Get sender user
	 *  
	 *  @return sender User sender
	 */
	public User getSender()
	{
		return sender;
	}

	/**
	 *  Set user sender
	 *
	 *  @param sender User sender
	 */
	public void setSender(User sender) 
	{
		this.sender = sender;
	}

	/**
	 *  Get receiver user
	 *  
	 *  @return receiver User receiver
	 */
	public User getReceiver()
	{
		return receiver;
	}

	/**
	 *  Set user receiver
	 *
	 *  @param receiver User receiver
	 */
	public void setReceiver(User receiver)
	{
		this.receiver = receiver;
	}

	/**
	 *  Get date and time
	 *  
	 *  @return dateTime LocalDateTime date and time
	 */
	public LocalDateTime getDateTime() 
	{
		return dateTime;
	}

	/**
	 *  Set date and time
	 *
	 *  @param dateTime LocalDateTime date and time
	 */
	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	/**
	 *  Get amount of the transaction
	 *  
	 *  @return amount BigDecimal amount of transaction
	 */
	public BigDecimal getAmount()
	{
		return amount;
	}

	/**
	 *  Set transaction amount
	 *
	 *  @param amount BigDecimal amount
	 */
	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	/**
	 *  Get message of the transaction
	 *  
	 *  @return description String description of transaction
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 *  Set transaction message
	 *
	 *  @param description String transaction message
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 *  Get amount of the transaction fee
	 *  
	 *  @return transactionFee BigDecimal fee amount of transaction 
	 */
	public BigDecimal getTransactionFee()
	{
		return transactionFee;
	}

	/**
	 *  Set transaction fee
	 *
	 *  @param transactionFee BigDecimal fee amount of transaction
	 */
	public void setTransactionFee(BigDecimal transactionFee)
	{
		this.transactionFee = transactionFee;
	}
}