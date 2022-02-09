//package com.paymybuddy.model;
//
//import java.util.Collections;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name ="user_friends")
//public class UserFriends
//{
//	@Id
//	@Column(name="id")
//	Integer id;
//	
//	@JoinColumn(name="user")
//	Integer user;
//	
//	@ManyToOne
//	@JoinColumn(name="friend")
//	User friend;
//		
//	public UserFriends()
//	{}
//	
//	public UserFriends(Integer user, User friend)
//	{
//		super();
//		this.user=user;
//		this.friend=friend;
//	}
//
//	public Integer getUser()
//	{
//		return user;
//	}
//
//	public void setUser(Integer user)
//	{
//		this.user = user;
//	}
//	
//	public User getFriend()
//	{
//		User noListUser = friend;
//		noListUser.setFriendsList(Collections.emptyList());
//		return noListUser;
//	}
//
//	public void setFriend(User friend)
//	{
//		User noListUser = friend;
//		noListUser.setFriendsList(Collections.emptyList());
//		this.friend = noListUser;
//	}
//}