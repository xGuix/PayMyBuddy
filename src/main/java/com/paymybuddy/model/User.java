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

	@OneToMany (mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserFriends> friendsList = new ArrayList<>();
	
	public User() 
	{}
	
	public User(Integer idUser, String firstname, String lastname, String city, String email, String password, float balance, 
			List<UserFriends> friendsList)
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

	public List<UserFriends> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(List<UserFriends> friendsList) {
		this.friendsList = friendsList;
	}
	
	public void addUserFriend(UserFriends friend)
	{
		friendsList.add(friend);
	}
 
	public void removeUserFriend(UserFriends friend)
	{
		friendsList.remove(friend);
	}
//	
//	public UserFriends getUser()
//	{
//		return usertomap;
//	}
//	public void setUser(UserFriends usertomap)
//	{
//		this.usertomap = usertomap;
//	}
//	public Map<User, UserFriends>  getFriendsList() 
//	{
//		return friendsList;
//	}
//	public void setFriendsList(Map<User, UserFriends> friendsList)
//	{
//		this.friendsList = friendsList;
//	}

	/***************************************************************/
	
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance){
		this.balance = balance;
	}
}