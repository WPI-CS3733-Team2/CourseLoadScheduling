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
public class ViewSectionCalendarsOfCourseDto
{
	private final Integer crn;
	private final String name;
	private final String type;
	private final Integer expected_population;
	private final Integer course_id;
	private final Integer schedule_id;
	
	// I added to the auto-generated code
	@Generated("SparkTools")
	private ViewSectionCalendarsOfCourseDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.crn = builder.crn;
		this.name = builder.name;
		this.type = builder.type;
		this.expected_population = builder.expected_population;
		this.course_id = builder.course_id;
		this.schedule_id = builder.schedule_id;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.crn == null) {
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
		else if(this.course_id == null)
		{
			throw new IllegalStateException("course_id cannot be null");
		}
	}
	
	public Integer getCrn() {
		return crn;
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

	public Integer getCourseId()
	{
		return course_id;
	}

	public Integer getScheduleId()
	{
		return schedule_id;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crn == null) ? 0 : crn.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((expected_population == null) ? 0 : expected_population.hashCode());
		result = prime * result + ((course_id == null) ? 0 : course_id.hashCode());
		result = prime * result +((schedule_id == null) ? 0 : schedule_id.hashCode());
		
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
		if (!(obj instanceof ViewSectionCalendarsOfCourseDto))
		{
			return false;
		}
		ViewSectionCalendarsOfCourseDto other = (ViewSectionCalendarsOfCourseDto) obj;
		if (crn == null)
		{
			if (other.crn != null)
			{
				return false;
			}
		}
		else if (!crn.equals(other.crn))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CreateSectionDto [crn=");
		builder.append(crn);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", expected_population=");
		builder.append(expected_population);
		builder.append(", course_id=");
		builder.append(course_id);
		builder.append(", schedule_id=");
		builder.append(schedule_id);
		builder.append(", year=");
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Creates builder to build {@link ViewSectionCalendarsOfCourseDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link ViewSectionCalendarsOfCourseDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private Integer crn;
		private String name;
		private String type;
		private Integer expected_population;
		private Integer course_id;
		private Integer schedule_id;
	
		private Builder()
		{
		}
		
		public Builder withCrn(Integer crn) {				//
			this.crn = crn;
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

		public Builder withCourseId(Integer course_id)
		{
			this.course_id = course_id;
			return this;
		}
		
		public Builder withScheduleId(Integer schedule_id)
		{
			this.schedule_id = schedule_id;
			return this;
		}
		
		public ViewSectionCalendarsOfCourseDto build()
		{
			return new ViewSectionCalendarsOfCourseDto(this);
		}
		
		
	}
}
