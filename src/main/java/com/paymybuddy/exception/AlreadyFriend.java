package com.paymybuddy.exception;

public class AlreadyFriend extends Exception
{
	private static final long serialVersionUID = -1042111602611144561L;

	/**
	 * Super constructor
	 * 
	 * @param s  String message 
	 */
	public AlreadyFriend (String s) 
	{
		super(s);
	}
}