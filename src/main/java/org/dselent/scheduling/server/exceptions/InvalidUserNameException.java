package org.dselent.scheduling.server.exceptions;

public class InvalidUserNameException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String invalidUserName;
	
	public InvalidUserNameException(String invalidUserName, String message)
	{
		super(message);
		this.invalidUserName = invalidUserName;
	}

	public String getInvalidUserName()
	{
		return invalidUserName;
	}

	public void setInvalidUserName(String invalidUserName)
	{
		this.invalidUserName = invalidUserName;
	}
}
