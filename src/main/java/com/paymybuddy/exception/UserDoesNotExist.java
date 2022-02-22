package com.paymybuddy.exception;

public class UserDoesNotExist extends Exception
{
	private static final long serialVersionUID = -1042111602611144561L;

	/**
	 * Super constructor
	 * 
	 * @param s  String message 
	 */
	public UserDoesNotExist (String s) 
	{
		super(s);
	}
}