package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 * 
 * @author dselent
 *
 */
public class RegisterUserDto
{
	private final String wpi_id;
	private final String userName;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String password;
	private final Integer roleId;
	private final Integer rank;

	// I added to the auto-generated code
	@Generated("SparkTools")
	private RegisterUserDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.wpi_id = builder.wpi_id;
		this.userName = builder.userName;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.password = builder.password;
		this.roleId = builder.roleId;
		this.rank = builder.rank;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.wpi_id == null) {
			throw new IllegalStateException("wpi_id cannot be null");
		}
		else if(this.userName == null)
		{
			throw new IllegalStateException("userName cannot be null");
		}
		else if(this.firstName == null)
		{
			throw new IllegalStateException("firstName cannot be null");
		}
		else if(this.lastName == null)
		{
			throw new IllegalStateException("lastName cannot be null");
		}
		else if(this.email == null)
		{
			throw new IllegalStateException("email cannot be null");
		}
		else if(this.password == null)
		{
			throw new IllegalStateException("password cannot be null");
		}
		else if(this.roleId == null) {
			//throw new IllegalStateException("role id cannot be null");
			//this.roleId = 1;
			throw new IllegalStateException("roleId cannot be null");
		}
		else if(this.rank == null) {
			throw new IllegalStateException("rank cannot be null");
		}
	}
	
	public String getWPIid() {
		return wpi_id;
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

	public String getPassword()
	{
		return password;
	}

	public Integer getRoleId() {
		return roleId;
	}
	
	public Integer getRank() {
		return rank;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegisterUserDto [wpi_id=" + wpi_id + ", userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", roleId=" + roleId
				+ ", rank=" + rank + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((wpi_id == null) ? 0 : wpi_id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterUserDto other = (RegisterUserDto) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (wpi_id == null) {
			if (other.wpi_id != null)
				return false;
		} else if (!wpi_id.equals(other.wpi_id))
			return false;
		return true;
	}

	/**
	 * Creates builder to build {@link RegisterUserDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link RegisterUserDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String wpi_id;
		private String userName;
		private String firstName;
		private String lastName;
		private String email;
		private String password;
		private Integer roleId;
		private Integer rank;

		private Builder()
		{
			rank = 0;
			roleId = 1;
		}
		
		public Builder withWPIid(String wpi_id) {				//
			this.wpi_id = wpi_id;
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

		public Builder withPassword(String password)
		{
			this.password = password;
			return this;
		}
		
		public Builder withRoleId(Integer roleId)
		{
			this.roleId = roleId;
			return this;
		}
		
		public Builder withRank(Integer rank) {
			this.rank = rank;
			return this;
		}

		public RegisterUserDto build()
		{
			return new RegisterUserDto(this);
		}
	}
}
