package com.paymybuddy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name ="user")
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7280829496449743887L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name="id_user")
	private int idUser;
	
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
	
	@OneToMany(mappedBy="userFriends",cascade = CascadeType.ALL)
	private List<User> userFriends = new ArrayList<>();

	public List<User> getUserFriends()
	{
		return userFriends;
	}

	public void setUserFriends(List<User> userFriends)
	{
		this.userFriends = userFriends;
	}
	
	public void addUserFriend(User user)
	{
		userFriends.add(user);
	}
	 
	public void removeUserFriend(User user)
	{
		userFriends.remove(user);
	}
	
	public int getIdUser()
	{
		return idUser;
	}

	public void setIdUser(int idUser)
	{
		this.idUser = idUser;
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

	@Override
	public String toString()
	{
		return "User ["
				+ "idUser=" + idUser 
				+ ", firstname=" + firstname 
				+ ", lastname=" + lastname 
				+ ", city=" + city
				+ ", email=" + email 
				+ ", password=" + password 
				+ ", balance=" + balance 
				+ ", userFriends="	+ userFriends
				+ "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(balance, city, email, firstname, idUser, lastname, password, userFriends);
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
		
		User other = (User) obj;		
		return Float.floatToIntBits(balance) == Float.floatToIntBits(other.balance) 
				&& Objects.equals(city, other.city)
				&& Objects.equals(email, other.email) 
				&& Objects.equals(firstname, other.firstname)
				&& idUser == other.idUser
				&& Objects.equals(lastname, other.lastname)
				&& Objects.equals(password, other.password) 
				&& Objects.equals(userFriends, other.userFriends);
	}
}