package com.paymybuddy.model;

/**
 *  Model Object Role
 */
public class Role
{
	private String name;
	
	/**
	 *  Default constructor
	 */
	public Role()
	{}
	
	/**
	 *  Specific constructor
	 *  
	 *  @param name Role name
	 */
	public Role(String name)
	{
		this.name = name;
	}
	
	/**
	 *  Get the role
	 *  
	 *  @return name String name
	 */
	public String getRole()
	{
		return name;
	}
	
	/**
	 * Set the role
	 * 
	 * @param name String name
	 */
	public void setRole(String name)
	{
		this.name = name;
	}
}