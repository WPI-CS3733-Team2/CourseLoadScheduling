package org.dselent.scheduling.server.exceptions;

public class InvalidPasswordException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	// one of these may be null
	private String userName;
	private Integer userId;
	
	public InvalidPasswordException(String userName, String message)
	{
		super(message);
		this.userName = userName;
	}
	
	public InvalidPasswordException(Integer userId, String message)
	{
		super(message);
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setInvalidUserName(String userName)
	{
		this.userName = userName;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
}
