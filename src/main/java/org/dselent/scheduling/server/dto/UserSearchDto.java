package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

import org.dselent.scheduling.server.dto.LoginUserDto.Builder;

/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 * 
 *
 *
 */
public class UserSearchDto
{
	private final String wpiId;
	private final String userName;
	private final String firstName;
	private final String lastName;
	private final String email;
	
	
	// I added to the auto-generated code
	@Generated("SparkTools")
	private UserSearchDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.wpiId = builder.wpiId;
		this.userName = builder.userName;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		
		// making claim that at least of one should have value... or not. It depends
//		if(this.wpiId == null || this.userName == null || this.firstName == null || this.lastName == null || this.email == null) {
//			throw new IllegalStateException("The search variables cannot be null.");
	}
	
	public String getWpiId() {
		return wpiId;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getEmail()
	{
		return email;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wpiId == null) ? 0 : wpiId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result +((email == null) ? 0 : email.hashCode());
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
		if (!(obj instanceof UserSearchDto))
		{
			return false;
		}
		UserSearchDto other = (UserSearchDto) obj;

		if (wpiId == null)
		{
			if (other.wpiId != null)
			{
				return false;
			}
		}
		else if (!wpiId.equals(other.wpiId))
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
		if (firstName == null)
		{
			if (other.firstName != null)
			{
				return false;
			}
		}
		else if (!firstName.equals(other.firstName))
		{
			return false;
		}
		if (lastName == null)
		{
			if (other.lastName != null)
			{
				return false;
			}
		}
		else if (!lastName.equals(other.lastName))
		{
			return false;
		}
		if (email == null)
		{
			if (other.email != null)
			{
				return false;
			}
		}
		else if (!email.equals(other.email))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("UserSearchDto [wpiId=");
		builder.append(wpiId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Creates builder to build {@link UserSearchDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link UserSearchDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String wpiId;
		private String userName;
		private String firstName;
		private String lastName;
		private String email;

		private Builder()
		{
		}
		
		public Builder withWpiId(String wpiId)
		{
			this.wpiId = wpiId;
			return this;
		}

		public Builder withUserName(String userName)
		{
			this.userName = userName;
			return this;
		}
		
		public Builder withFirstName(String firstName)
		{
			this.firstName = firstName;
			return this;
		}
		
		public Builder withLastName(String lastName)
		{
			this.lastName = lastName;
			return this;
		}
		
		public Builder withEmail(String email)
		{
			this.email = email;
			return this;
		}

		public UserSearchDto build()
		{
			return new UserSearchDto(this);
		}
	}
}
