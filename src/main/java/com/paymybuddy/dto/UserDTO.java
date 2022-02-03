package com.paymybuddy.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1102136054514744356L;
	
	private int idUser;
	private String firstname;
	private String lastname;
	private String city;
	private String email;
	private float balance;
	
    public UserDTO()
    {}
    
	public UserDTO(int idUser, String firstname, String lastname,String city, String email, float balance)
	{
		this.idUser = idUser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.email = email;
		this.balance = balance;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
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
				&& Objects.equals(email, other.email) 
				&& Objects.equals(firstname, other.firstname)
				&& idUser == other.idUser
				&& Objects.equals(lastname, other.lastname);
	}
}