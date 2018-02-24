package org.dselent.scheduling.server.exceptions;

public class InvalidPasswordException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	private String userName;
	
	public InvalidPasswordException(String userName, String message)
	{
		super(message);
		this.userName = userName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setInvalidUserName(String userName)
	{
		this.userName = userName;
	}
}
