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
public class ModifySectionTypeNamePopDto
{
	private final Integer id;
	private final String type;
	private final String name;
	private final Integer expected_population;

	// I added to the auto-generated code
	@Generated("SparkTools")
	private ModifySectionTypeNamePopDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.id = builder.id;
		this.type = builder.type;
		this.name = builder.name;
		this.expected_population = builder.expected_population;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.id == null) {
			throw new IllegalStateException("crn cannot be null");
		}
		else if(this.name == null)
		{
			throw new IllegalStateException("name cannot be null");
		}
		else if(this.type == null)
		{
			throw new IllegalStateException("type cannot be null");
		}
		else if(this.expected_population == null)
		{
			throw new IllegalStateException("expected_population cannot be null");
		}
	}
	
	public Integer getId() {
		return id;
	}
	public String getName()
	{
		return name;
	}

	public String getType()
	{
		return type;
	}

	public Integer getExpectedPopulation()
	{
		return expected_population;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((expected_population == null) ? 0 : expected_population.hashCode());
		
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
		if (!(obj instanceof ModifySectionTypeNamePopDto))
		{
			return false;
		}
		ModifySectionTypeNamePopDto other = (ModifySectionTypeNamePopDto) obj;
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
		builder.append("ModifySectionTypeNamePopDto [id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append(", name=");
		builder.append(name);
		builder.append(", expected_population=");
		builder.append(expected_population);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Creates builder to build {@link ModifySectionTypeNamePopDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link ModifySectionTypeNamePopDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private Integer id;
		private String name;
		private String type;
		private Integer expected_population;

		private Builder()
		{
		}
		
		public Builder withId(Integer id2) {				//
			this.id = id2;
			return this;
		}
		
		public Builder withName(String name)
		{
			this.name = name;
			return this;
		}

		public Builder withType(String type)
		{
			this.type = type;
			return this;
		}

		public Builder withExpectedPopulation(Integer expected_population)
		{
			this.expected_population = expected_population;
			return this;
		}

		public ModifySectionTypeNamePopDto build()
		{
			return new ModifySectionTypeNamePopDto(this);
		}
	}
}
