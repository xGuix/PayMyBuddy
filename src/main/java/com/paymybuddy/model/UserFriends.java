package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="user_friends")
public class UserFriends
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_user_friends")
	private int idUserFriends;
	
	@Column(name="id_user")
	private int idUser;
	
	@Column(name="id_friend")
	private String idFriend;

	public int getIdUserFriends()
	{
		return idUserFriends;
	}

	public void setIdUserFriends(int idUserFriends)
	{
		this.idUserFriends = idUserFriends;
	}

	public int getIdUser()
	{
		return idUser;
	}

	public void setIdUser(int idUser)
	{
		this.idUser = idUser;
	}

	public String getIdFriend()
	{
		return idFriend;
	}

	public void setIdFriend(String idFriend)
	{
		this.idFriend = idFriend;
	}
}