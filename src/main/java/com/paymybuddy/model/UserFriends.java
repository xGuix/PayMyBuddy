package com.paymybuddy.model;

import javax.persistence.Column;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	Integer id;
	
	@Column(name="id_user")
	Integer user;

	@ManyToOne
	@JoinColumn(name="id_friend")
	User friend;
		
	public UserFriends()
	{}
	
	public UserFriends(Integer id,Integer user, User friend)
	{
		super();
		this.id=id;
		this.user=user;
		this.friend=friend;
	}
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getUser()
	{
		return user;
	}

	public void setUser(Integer user)
	{
		this.user = user;
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