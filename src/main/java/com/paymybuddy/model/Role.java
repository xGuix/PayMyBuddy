package com.paymybuddy.model;

public class Role
{
	private String name;
	
	public Role()
	{}
	
	public Role(String name)
	{
		this.name = name;
	}
	
	public String getRole()
	{
		return name;
	}
	public void setRole(String name)
	{
		this.name = name;
	}
}