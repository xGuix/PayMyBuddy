package com.paymybuddy.dto;

import java.util.Objects;

public class UserDTO
{
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private float balance;
	
    public UserDTO() {}
    
	public UserDTO(int id, String firstName, String lastName, String email, float balance)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.balance = balance;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
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
		return "UserDTO [id=" + id 
				+ ", firstName=" + firstName 
				+ ", lastName=" + lastName 
				+ ", email=" + email
				+ ", balance=" + balance 
				+ "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(balance, email, firstName, id, lastName);
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
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName);
	}
}