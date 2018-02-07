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
public class SearchCourseDto
{
	private final String name;
	private final String number;
	private final String frequency;
	private final String id;

	// I added to the auto-generated code
	@Generated("SparkTools")
	private SearchCourseDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.name = builder.name;
		this.number = builder.number;
		this.frequency = builder.frequency;
		this.id = builder.id;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.name == null) {
			throw new IllegalStateException("name cannot be null");
		}
		else if(this.number == null)
		{
			throw new IllegalStateException("number cannot be null");
		}
		else if(this.frequency == null)
		{
			throw new IllegalStateException("frequency cannot be null");
		}
		else if(this.id == null)
		{
			throw new IllegalStateException("id cannot be null");
		}
	}
	
	public String getName() {
		return name;
	}
	public String getNumber()
	{
		return number;
	}

	public String getFrequency()
	{
		return frequency;
	}
	
	public String getId()
	{
		return id;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
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
		if (!(obj instanceof SearchCourseDto))
		{
			return false;
		}
		SearchCourseDto other = (SearchCourseDto) obj;
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
		if (frequency == null)
		{
			if (other.frequency != null)
			{
				return false;
			}
		}
		else if (!frequency.equals(other.frequency))
		{
			return false;
		}
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		}
		else if (!id.equals(other.id))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SearchCourseDto [name=");
		builder.append(name);
		builder.append(", number=");
		builder.append(number);
		builder.append(", frequency=");
		builder.append(frequency);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Creates builder to build {@link SearchCourseDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link SearchCourseDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String id;
		private String name;
		private String number;
		private String frequency;
		
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

		public Builder withFrequency(String frequency)
		{
			this.frequency = frequency;
			return this;
		}
		
		public Builder withId(String id)
		{
			this.id = id;
			return this;
		}

		public SearchCourseDto build()
		{
			return new SearchCourseDto(this);
		}
	}
}
