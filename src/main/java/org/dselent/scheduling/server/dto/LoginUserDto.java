package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 * 
 *
 *
 */
public class LoginUserDto
{
	private final String userName;
	private final String password;
	
	// I added to the auto-generated code
	@Generated("SparkTools")
	private LoginUserDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.userName = builder.userName;
		this.password = builder.password;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.userName == null) {
			throw new IllegalStateException("userName cannot be null");
		}
		else if(this.password == null)
		{
			throw new IllegalStateException("password cannot be null");
		}
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword()
	{
		return password;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result +((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof LoginUserDto))
		{
			return false;
		}
		LoginUserDto other = (LoginUserDto) obj;

		if (password == null)
		{
			if (other.password != null)
			{
				return false;
			}
		}
		else if (!password.equals(other.password))
		{
			return false;
		}
		if (userName == null)
		{
			if (other.userName != null)
			{
				return false;
			}
		}
		else if (!userName.equals(other.userName))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("LoginUserDto [userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Creates builder to build {@link LoginUserDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link LoginUserDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String userName;
		private String password;

		private Builder()
		{
		}
		
		public Builder withUserName(String userName)
		{
			this.userName = userName;
			return this;
		}

		public Builder withPassword(String password)
		{
			this.password = password;
			return this;
		}

		public LoginUserDto build()
		{
			return new LoginUserDto(this);
		}
	}
}
