package com.paymybuddy.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="user_friends")
public class UserFriends
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_user")
	private User idUser;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_friend")
	private User idFriend;
		
	public UserFriends()
	{}
	
	public UserFriends(int id, User idUser,User idFriend)
	{
		super();
		this.id = id;
		this.idUser= idUser;
		this.idFriend = idFriend;
	}

	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}

	public User getIdUser()
	{
		return idUser;
	}

	public void setIdUser(User idUser)
	{
		this.idUser = idUser;
	}

	public User getIdFriend()
	{
		return idFriend;
	}

	public void setIdFriend(User idFriend)
	{
		this.idFriend = idFriend;
	}
}