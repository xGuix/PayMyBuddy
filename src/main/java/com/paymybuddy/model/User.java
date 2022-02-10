package com.paymybuddy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class User
{
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
	private float balance;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name ="bankaccount", joinColumns = @JoinColumn(name ="bankaccount_id"),
			inverseJoinColumns = @JoinColumn(name ="user_id"))
	private BankAccount bankAccount;
	
	@OneToMany (fetch = FetchType.LAZY)
	@JoinTable(name ="connection", joinColumns = @JoinColumn(name ="user_id"),
			inverseJoinColumns = @JoinColumn(name ="connection_id"))
	List<User> friendsList = new ArrayList<>();
	
	public User() 
	{}
	
	public User(Integer userId, String firstname, String lastname,
			String city, String email, String password, float balance, 
			List<User> friendsList)
	{
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.friendsList=friendsList;
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

	public float getBalance()
	{
		return balance;
	}

	public void setBalance(float balance)
	{
		this.balance = balance;
	}
}