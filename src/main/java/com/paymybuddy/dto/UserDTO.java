package com.paymybuddy.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  User DTO Model object
 */
public class UserDTO
{
	private Integer userId;
	private String firstname;
	private String lastname;
	private String city;
	private String email;
	private BigDecimal balance;

	private List<UserDTO> connections = new ArrayList<>();
	
	/**
	 *  Default constructor
	 */
    public UserDTO()
    {}
    
	/**
	 *  Specific constructor
	 *  
	 *  @param userId Integrer Id user
	 *  @param firstname String first name
	 *  @param lastname String last name
	 *  @param city String city
	 *  @param email String email
	 *  @param balance BigDecimal balance
	 *  @param connections List of user friends
	 */
	public UserDTO(Integer userId, String firstname, String lastname,String city,
			String email, BigDecimal balance, List<UserDTO> connections)
	{
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.email = email;
		this.balance = balance;
		this.connections = connections;
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
	 *  Set user Id
	 *  
	 *  @param userId Integer user Id
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
	 *  @param firstName String first name
	 */
	public void setFirstname(String firstName)
	{
		this.firstname = firstName;
	}

	/**
	 *  Get user last name
	 *  
	 *  @return lastname String last name
	 */
	public String getLastname()
	{
		return lastname;
	}

	/**
	 *  Set user last name
	 *  
	 *  @param lastName String last name
	 */
	public void setLastname(String lastName)
	{
		this.lastname = lastName;
	}

	/**
	 *  Get user city
	 *  
	 *  @return city String city
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
	 *  Get user email
	 *  
	 *  @return email String email
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
	 *  Get user balance
	 *  
	 *  @return balance BigDecimal email
	 */
	public BigDecimal getBalance()
	{
		return balance;
	}

	/**
	 *  Set user balance
	 *  
	 *  @param balance BigDecimal balance
	 */
	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}

	/**
	 *  Get user friends list
	 *  
	 *  @return connections UserDTO friends list
	 */
	public List<UserDTO> getFriendsList()
	{
		return connections;
	}

	/**
	 *  Set user friends list
	 *  
	 *  @param connections UserDTO List of friends
	 */
	public void setFriendsList(List<UserDTO> connections)
	{
		this.connections = connections;	
	}
}