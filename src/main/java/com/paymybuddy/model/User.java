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

@Entity
@DynamicUpdate
@Table(name ="user")
public class User implements Serializable
{
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8623408266096690763L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="city")
	private String city;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="balance")
	private BigDecimal balance;

	@OneToOne
	@JoinColumn(name="bankaccountId",referencedColumnName = "bankaccount_id")
	private BankAccount bankAccount;
	
	@OneToMany (fetch = FetchType.LAZY)
	@JoinTable(name ="connection", joinColumns = @JoinColumn(name ="user_id"),
			inverseJoinColumns = @JoinColumn(name ="connection_id"))
	private List<User> friendsList = new ArrayList<>();
			
	public User() 
	{}
	
	public User(Integer userId, String firstname, String lastname,
			String email, BigDecimal balance) 
	{
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.balance = balance;
	}
	
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
	
	
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
	
	public BankAccount getBankAccount()
	{
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount)
	{
		this.bankAccount = bankAccount;
	}
	
	public BankAccount addbankAccount(BankAccount bankAccount)
	{
		return bankAccount;
	}
	
	public List<User> getFriendsList()
	{
		return friendsList;
	}

	public void setFriendsList(List<User> friendsList)
	{
		this.friendsList = friendsList;
	}
	
	public void addUserFriend(User friend)
	{
		friendsList.add(friend);
	}
 
	public void removeUserFriend(User friend)
	{
		friendsList.remove(friend);
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getCity() 
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public BigDecimal getBalance()
	{
		return balance;
	}

	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}
	
	public BigDecimal addMoney(BigDecimal deposite)
	{
		return balance.add(deposite);
	}
	
}