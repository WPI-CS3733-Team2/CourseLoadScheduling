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
public class DeleteCourseDto
{
	private final String name;
	private final String number;

	// I added to the auto-generated code
	@Generated("SparkTools")
	private DeleteCourseDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.name = builder.name;
		this.number = builder.number;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.name == null) {
			throw new IllegalStateException("name cannot be null");
		}
		else if(this.number == null)
		{
			throw new IllegalStateException("number cannot be null");
		}	
	}
	
	public String getName() {
		return name;
	}
	public String getNumber()
	{
		return number;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		if (!(obj instanceof DeleteCourseDto))
		{
			return false;
		}
		DeleteCourseDto other = (DeleteCourseDto) obj;
		if (name == null)
		{
			if (other.name != null)
			{
				return false;
			}
		}
		else if (!name.equals(other.name))
		{
			return false;
		}
		if (number == null)
		{
			if (other.number != null)
			{
				return false;
			}
		}
		else if (!number.equals(other.number))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CreateCourseDto [name=");
		builder.append(name);
		builder.append(", number=");
		builder.append(number);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Creates builder to build {@link DeleteCourseDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link DeleteCourseDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String name;
		private String number;
		
		private Builder()
		{
		}
		
		public Builder withName(String name) {				//
			this.name = name;
			return this;
		}
		
		public Builder withNumber(String number)
		{
			this.number = number;
			return this;
		}

		public DeleteCourseDto build()
		{
			return new DeleteCourseDto(this);
		}
	}
}
