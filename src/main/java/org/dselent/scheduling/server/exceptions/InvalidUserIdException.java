package org.dselent.scheduling.server.exceptions;

public class InvalidUserIdException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	private Integer invalidUserId;
	
	public InvalidUserIdException(Integer invalidUserId, String message)
	{
		super(message);
		this.invalidUserId = invalidUserId;
	}

	public Integer getInvalidUserId()
	{
		return invalidUserId;
	}

	public void setInvalidUserId(Integer invalidUserId)
	{
		this.invalidUserId = invalidUserId;
	}
}
