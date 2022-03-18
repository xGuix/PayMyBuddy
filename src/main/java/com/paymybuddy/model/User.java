package com.paymybuddy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import groovy.transform.Generated;

/**
 *  Model Object User
 */
@Entity
@Generated
@DynamicUpdate
@Table(name ="user")
public class User implements Serializable
{
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8623408266096690763L;

	/**
	 *  User Id DB field
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	/**
	 * User first name DB field
	 */
	@Column(name="firstname")
	private String firstname;
	
	/**
	 * User last name DB field
	 */
	@Column(name="lastname")
	private String lastname;
	
	/**
	 * User city DB field
	 */
	@Column(name="city")
	private String city;
	
	/**
	 * User email DB field
	 */
	@Column(name="email")
	private String email;
	
	/**
	 * User password DB field
	 */
	@Column(name="password")
	private String password;
	
	/**
	 * User balance DB field
	 */
	@Column(name="balance")
	private BigDecimal balance;

	/**
	 * Bank account table link
	 */
	@OneToOne
	@JoinColumn(name="bankaccountId",referencedColumnName = "bankaccount_id")
	private BankAccount bankAccount;
	
	/**
	 * User friends list table link
	 */
	@OneToMany (fetch = FetchType.LAZY)
	@JoinTable(name ="connection", joinColumns = @JoinColumn(name ="user_id"),
			inverseJoinColumns = @JoinColumn(name ="connection_id"))
	private List<User> friendsList = new ArrayList<>();
	
	/**
	 *  Default Constructor
	 */
	public User() 
	{}
	
	/**
	 *  Specific Constructor 
	 *  
	 *  @param userId Integrer Id user
	 *  @param firstname String first name
	 *  @param lastname String last name
	 *  @param email String email
	 *  @param balance BigDecimal balance
	 */
	public User(Integer userId, String firstname, String lastname,
			String email, BigDecimal balance) 
	{
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.balance = balance;
	}
	
	/**
	 *  Specific constructor
	 *  
	 *  @param firstname String first name
	 *  @param lastname String last name
	 *  @param city String city
	 *  @param email String email
	 *  @param password String password
	 *  @param balance BigDecimal balance
	 *  @param friendsList List of user friends
	 */
	public User(String firstname, String lastname, String city,
			String email, String password, BigDecimal balance, 
			List<User> friendsList)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.friendsList=friendsList;
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
	 *  Get bank account
	 *  
	 *  @return bankaccount BankAccount bank account
	 */
	public BankAccount getBankAccount()
	{
		return bankAccount;
	}

	/**
	 *  Set bank account for user
	 *  
	 *  @param bankAccount BankAccount bank account
	 */
	public void setBankAccount(BankAccount bankAccount)
	{
		this.bankAccount = bankAccount;
	}
		
	/**
	 *  Get friends list
	 *  
	 *  @return friendsList User list of friends
	 */
	public List<User> getFriendsList()
	{
		return friendsList;
	}

	/**
	 *  Set friends list for user
	 *  @param friendsList List of friends
	 */
	public void setFriendsList(List<User> friendsList)
	{
		this.friendsList = friendsList;
	}
	
	/**
	 *  Add a user to friends list
	 *  
	 *  @param friend User for friends
	 */
	public void addUserFriend(User friend)
	{
		friendsList.add(friend);
	}
 
	/**
	 *  Remove a user to friends list
	 *  
	 *  @param friend User from friends
	 */
	public void removeUserFriend(User friend)
	{
		friendsList.remove(friend);
	}

	/**
	 *  Get User Id
	 *  
	 *  @return userId Integer Id
	 */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * Set user id
	 * 
	 * @param userId Integer
	 */
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	/**
	 *  Get user first name
	 *  
	 *  @return firstname String first name
	 */
	public String getFirstname()
	{
		return firstname;
	}

	/**
	 *  Set user first name
	 *
	 *  @param firstname String first name
	 */
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	
	/**
	 * Get user last name
	 * 
	 * @return lastname String last name
	 */
	public String getLastname()
	{
		return lastname;
	}

	/**
	 *  Set user last name
	 *
	 *  @param lastname String last name
	 */
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	/**
	 * Get user city
	 * 
	 * @return city String city
	 */
	public String getCity() 
	{
		return city;
	}

	/**
	 *  Set user city
	 *
	 *  @param city String city
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * Get user email
	 * 
	 * @return email String email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 *  Set user email
	 *
	 *  @param email String email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Get user password
	 * 
	 * @return password String password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 *  Set user password
	 *
	 *  @param password String password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * Get user balance
	 * 
	 * @return balance BigDecimal balance
	 */
	public BigDecimal getBalance()
	{
		return balance;
	}

	/**
	 *  Set user balance
	 *
	 *  @param balance String balance
	 */
	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}
}