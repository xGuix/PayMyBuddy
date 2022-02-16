package com.paymybuddy.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO
{
	private Integer userId;
	private String firstname;
	private String lastname;
	private String city;
	private String email;
	private float balance;

	private List<UserDTO> connections = new ArrayList<>();
	
    public UserDTO()
    {}
    
	public UserDTO(Integer userId, String firstname, String lastname,String city,
			String email, float balance, List<UserDTO> connections)
	{
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.email = email;
		this.balance = balance;
		this.connections = connections;
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

	public void setFirstname(String firstName)
	{
		this.firstname = firstName;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastName)
	{
		this.lastname = lastName;
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

	public float getBalance()
	{
		return balance;
	}

	public void setBalance(float balance)
	{
		this.balance = balance;
	}

	public List<UserDTO> getFriendsList()
	{
		return connections;
	}

	public void setFriendsList(List<UserDTO> connections)
	{
		this.connections = connections;	
	}
}