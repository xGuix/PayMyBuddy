package com.paymybuddy.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="user_friends")
public class UserFriends
{
	@Id
	@JoinColumn(name="id_user")
	private Integer idUser;
	
	@ManyToOne
	@JoinColumn(name="id_friend", insertable = false, updatable = false)
	private User friend;
		
	public UserFriends()
	{}
	
	public UserFriends(Integer idUser, User friend)
	{
		super();
		this.idUser= idUser;
		this.friend = friend;
	}

	public Integer getIdUser()
	{
		return idUser;
	}

	public void setIdUser(Integer idUser)
	{
		this.idUser = idUser;
	}

	public User getFriend()
	{
		return friend;
	}

	public void setFriend(User friend)
	{
		this.friend = friend;
	}
}