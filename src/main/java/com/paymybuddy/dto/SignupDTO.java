package com.paymybuddy.dto;

/**
 *  User DTO Model Object
 */
public class SignupDTO
{
	private String firstname;
	private String lastname;
	private String city;
	private String email;
	private String password;
	
	/**
	 *  Default constructor
	 */
	public SignupDTO()
	{}
		
	/**
	 *  Specific constructor
	 *  
	 *  @param firstname String first name
	 *  @param lastname String last name
	 *  @param city String city
	 *  @param email String email
	 *  @param password String password
	 */
	public SignupDTO(String firstname, String lastname, String city, String email, String password)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.email = email;
		this.password = password;
	}
		
	/**
	 *  Get user first name
	 *  
	 *  @return firstname User first name
	 */
	public String getFirstname()
	{
		return firstname;
	}
	
	/**
	 *  Set user first name
	 *  
	 *  @param firstname String first name
	 */
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	
	/**
	 *  Get user last name
	 *  
	 *  @return lastname User last name
	 */
	public String getLastname()
	{
		return lastname;
	}
	
	/**
	 *  Set user last name
	 *  
	 *  @param lastname String las name
	 */
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	
	/**
	 *  Get user city
	 *  
	 *  @return city User city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 *  Set user city
	 *  
	 *  @param city String city
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 *  Get user email
	 *  
	 *  @return email User email
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 *  Set user email
	 *  
	 *  @param email String email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 *  Get user password
	 *  
	 *  @return password User password
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 *  Set user password
	 *  
	 *  @param password String password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}