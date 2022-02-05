package com.paymybuddy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name ="user")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_user")
	private Integer idUser;
	
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
	
	@OneToMany (mappedBy="idUser",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserFriends> friendsList = new ArrayList<>();
	
	public User() 
	{}
	
	public User(Integer idUser, String firstname, String lastname, String city, String email, String password,
			float balance, List<UserFriends> friendsList)
	{
		super();
		this.idUser = idUser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.friendsList = friendsList;
	}

	
	public List<UserFriends> getFriendsList()
	{
		return friendsList;
	}

	public void setFriendsList(List<UserFriends> friendsList)
	{
		this.friendsList = friendsList;
	}

	public void addUserFriend(UserFriends idUser)
	{
		friendsList.add(idUser);
	}
 
	public void removeUserFriend(UserFriends idUser)
	{
		friendsList.remove(idUser);
	}
	
	public Integer getIdUser()
	{
		return idUser;
	}

	public void setIdUser(Integer idUser)
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
				+ ", friendsList=" + friendsList 
				+ "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(idUser,firstname,lastname,balance, city, email, password, friendsList );
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
				&& Objects.equals(friendsList, other.friendsList)
				&& Objects.equals(idUser, other.idUser)
				&& Objects.equals(lastname, other.lastname) 
				&& Objects.equals(password, other.password);
	}
}