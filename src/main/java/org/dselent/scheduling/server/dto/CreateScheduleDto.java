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
public class CreateScheduleDto
{
	private final String faculty_id;
	private final String schedule_name;


	// I added to the auto-generated code
	@Generated("SparkTools")
	private CreateScheduleDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.faculty_id = builder.faculty_id;
		this.schedule_name = builder.schedule_name;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.faculty_id == null) {
			throw new IllegalStateException("crn cannot be null");
		}
		else if(this.schedule_name == null)
		{
			throw new IllegalStateException("name cannot be null");
		}
	}
	
	public String getFacultyId() {
		return faculty_id;
	}
	public String getScheduleName()
	{
		return schedule_name;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((faculty_id == null) ? 0 : faculty_id.hashCode());
		result = prime * result + ((schedule_name == null) ? 0 : schedule_name.hashCode());
		
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
		if (!(obj instanceof CreateScheduleDto))
		{
			return false;
		}
		CreateScheduleDto other = (CreateScheduleDto) obj;
		if (schedule_name == null)
		{
			if (other.schedule_name != null)
			{
				return false;
			}
		}
		else if (!schedule_name.equals(other.schedule_name))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CreateSectionDto [faculty_id=");
		builder.append(faculty_id);
		builder.append(", schedule_name=");
		builder.append(schedule_name);
		return builder.toString();
	}

	/**
	 * Creates builder to build {@link CreateScheduleDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link CreateScheduleDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String faculty_id;
		private String schedule_name;

		private Builder()
		{
		}
		
		public Builder withFacultyId(String facultyId) {	
			this.faculty_id = facultyId;
			return this;
		}
		
		public Builder withScheduleName(String schedule_name)
		{
			this.schedule_name = schedule_name;
			return this;
		}

		public CreateScheduleDto build()
		{
			return new CreateScheduleDto(this);
		}
	}
}
