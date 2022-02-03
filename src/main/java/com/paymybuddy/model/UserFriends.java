package com.paymybuddy.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="user_friends")
public class UserFriends implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3012902785436011068L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name= "id_user")
	private UserFriends idUser;
	
	@ManyToOne
	@JoinColumn(name="id_friend")
	private UserFriends idFriend;
	
	public UserFriends()
	{}
	
	public UserFriends(UserFriends idUser, UserFriends idFriend)
	{
		this.idUser = idUser;
		this.idFriend = idFriend;
	} 

	public int getIdUserFriends()
	{
		return id;
	}

	public void setIdUserFriends(int idUserFriends)
	{
		this.id = idUserFriends;
	}

	public UserFriends getIdUser()
	{
		return idUser;
	}

	public void setIdUser(UserFriends idUser)
	{
		this.idUser = idUser;
	}

	public UserFriends getIdFriend()
	{
		return idFriend;
	}

	public void setIdFriend(UserFriends idFriend)
	{
		this.idFriend = idFriend;
	}

	@Override
	public String toString()
	{
		return "UserFriends ["
				+ "id=" + id
				+ ", idUser=" + idUser 
				+ ", idFriend=" + idFriend
				+ "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(idFriend, idUser, id);
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
		
		UserFriends other = (UserFriends) obj;
		return idFriend == other.idFriend 
				&& idUser == other.idUser 
				&& id == other.id;
	}
}