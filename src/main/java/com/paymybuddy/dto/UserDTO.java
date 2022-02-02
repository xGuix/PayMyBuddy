package com.paymybuddy.dto;

import java.util.Objects;

public class UserDTO
{
	private int idUser;
	private String firstname;
	private String lastname;
	private String email;
	private float balance;
	
    public UserDTO()
    {}
    
	public UserDTO(int idUser, String firstname, String lastname, String email, float balance)
	{
		super();
		this.idUser = idUser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.balance = balance;
	}

	public int getId()
	{
		return idUser;
	}

	public void setId(int idUser)
	{
		this.idUser = idUser;
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
	
	@Override
	public String toString()
	{
		return "UserDTO [id=" + idUser 
				+ ", firstName=" + firstname 
				+ ", lastName=" + lastname 
				+ ", email=" + email
				+ ", balance=" + balance 
				+ "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(balance, email, firstname, idUser, lastname);
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
		
		UserDTO other = (UserDTO) obj;
		
		return Float.floatToIntBits(balance) == Float.floatToIntBits(other.balance)
				&& Objects.equals(email, other.email) && Objects.equals(firstname, other.firstname) && idUser == other.idUser
				&& Objects.equals(lastname, other.lastname);
	}
}