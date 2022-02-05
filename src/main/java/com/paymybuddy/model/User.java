package com.paymybuddy.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@OneToMany (mappedBy= "idUser")
	private Set<UserFriends> usersList = new HashSet<>();
	
	@OneToMany (mappedBy= "idFriend")
	private Set<UserFriends> friendsList = new HashSet<>();
	
	public Set<UserFriends> getUsersList()
	{
		return usersList;
	}

	public void setUsersList(Set<UserFriends> usersList)
	{
		this.usersList = usersList;
	}

	public Set<UserFriends> getFriendsList()
	{
		return friendsList;
	}

	public void setFriendsList(Set<UserFriends> friendsList)
	{
		this.friendsList = friendsList;
	}
	
	public void addUserFriend(UserFriends idUser)
	{
		usersList.add(idUser);
	}
 
	public void removeUserFriend(UserFriends idUser)
	{
		usersList.remove(idUser);
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
}