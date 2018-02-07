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
public class PasswordModificationDto
{
	private final String userName;
	private final String oldPassword;
	private final String newPassword;
	
	// I added to the auto-generated code
		@Generated("SparkTools")
		private PasswordModificationDto(Builder builder)
		{
			// can add defaults if null for other places where the builder pattern is used
			this.userName = builder.userName;
			this.oldPassword = builder.oldPassword;
			this.newPassword = builder.newPassword;
			
			// making claim that none of these can be null
			// add other state checks here as necessary
			if(this.userName == null) {
				throw new IllegalStateException("userName cannot be null");
			}
			else if(this.oldPassword == null)
			{
				throw new IllegalStateException("oldPassword cannot be null");
			}
			else if(this.newPassword == null)
			{
				throw new IllegalStateException("newPassword cannot be null");
			}
		}
		
		public String getUserName() {
			return userName;
		}
		
		public String getOldPassword()
		{
			return oldPassword;
		}
		
		public String getNewPassword()
		{
			return newPassword;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
			result = prime * result + ((oldPassword == null) ? 0 : oldPassword.hashCode());
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
			if (!(obj instanceof PasswordModificationDto))
			{
				return false;
			}
			PasswordModificationDto other = (PasswordModificationDto) obj;

			if (oldPassword == null)
			{
				if (other.oldPassword != null)
				{
					return false;
				}
			}
			else if (!oldPassword.equals(other.oldPassword))
			{
				return false;
			}
			if (newPassword == null)
			{
				if (other.newPassword != null)
				{
					return false;
				}
			}
			else if (!newPassword.equals(other.newPassword))
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
			builder.append("PasswordModificationDto [userName=");
			builder.append(userName);
			builder.append(", oldPassword=");
			builder.append(oldPassword);
			builder.append(", newPassword=");
			builder.append(newPassword);
			builder.append("]");
			return builder.toString();
		}

		/**
		 * Creates builder to build {@link PasswordModificationDto}.
		 * @return created builder
		 */
		@Generated("SparkTools")
		public static Builder builder()
		{
			return new Builder();
		}

		/**
		 * Builder to build {@link PasswordModificationDto}.
		 */
		@Generated("SparkTools")
		public static final class Builder
		{
			private String userName;
			private String oldPassword;
			private String newPassword;

			private Builder()
			{
			}
			
			public Builder withUserName(String userName)
			{
				this.userName = userName;
				return this;
			}

			public Builder withOldPassword(String oldPassword)
			{
				this.oldPassword = oldPassword;
				return this;
			}
			public Builder withNewPassword(String newPassword)
			{
				this.newPassword = newPassword;
				return this;
			}

			public PasswordModificationDto build()
			{
				return new PasswordModificationDto(this);
			}
		}
}
