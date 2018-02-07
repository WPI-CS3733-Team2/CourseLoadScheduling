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
public class ModifySectionCalendarDto
{
	private final String id;
	private final String year;
	private final String semester;
	private final String days;
	private final String start_time;
	private final String end_time;

	// I added to the auto-generated code
	@Generated("SparkTools")
	private ModifySectionCalendarDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.id = builder.id;
		this.year = builder.year;
		this.semester = builder.semester;
		this.days = builder.days;
		this.start_time = builder.start_time;
		this.end_time = builder.end_time;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.id == null) {
			throw new IllegalStateException("id cannot be null");
		}
		else if(this.year == null)
		{
			throw new IllegalStateException("year cannot be null");
		}
		else if(this.semester == null)
		{
			throw new IllegalStateException("semester cannot be null");
		}
		else if(this.days == null)
		{
			throw new IllegalStateException("days cannot be null");
		}
		else if(this.start_time == null)
		{
			throw new IllegalStateException("start_time cannot be null");
		}
		else if(this.end_time == null)
		{
			throw new IllegalStateException("end_time cannot be null");
		}
	}
	
	public String getId() {
		return id;
	}

	public String getYear() {
		return year;
	}

	public String getSemester() {
		return semester;
	}

	public String getDays() {
		return days;
	}

	public String getStart_time() {
		return start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((days == null) ? 0 : days.hashCode());
		result = prime * result + ((end_time == null) ? 0 : end_time.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((semester == null) ? 0 : semester.hashCode());
		result = prime * result + ((start_time == null) ? 0 : start_time.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModifySectionCalendarDto other = (ModifySectionCalendarDto) obj;
		if (days == null) {
			if (other.days != null)
				return false;
		} else if (!days.equals(other.days))
			return false;
		if (end_time == null) {
			if (other.end_time != null)
				return false;
		} else if (!end_time.equals(other.end_time))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (semester == null) {
			if (other.semester != null)
				return false;
		} else if (!semester.equals(other.semester))
			return false;
		if (start_time == null) {
			if (other.start_time != null)
				return false;
		} else if (!start_time.equals(other.start_time))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModifySectionCalendarDto [id=");
		builder.append(id);
		builder.append(", year=");
		builder.append(year);
		builder.append(", semester=");
		builder.append(semester);
		builder.append(", days=");
		builder.append(days);
		builder.append(", start_time=");
		builder.append(start_time);
		builder.append(", end_time=");
		builder.append(end_time);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Creates builder to build {@link ModifySectionCalendarDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link ModifySectionCalendarDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String id;
		private String year;
		private String semester;
		private String days;
		private String start_time;
		private String end_time;

		private Builder()
		{
		}
		
		public Builder withId(String id) {				//
			this.id = id;
			return this;
		}
		
		public Builder withYear(String year)
		{
			this.year = year;
			return this;
		}

		public Builder withSemester(String semester)
		{
			this.semester = semester;
			return this;
		}

		public Builder withDays(String days)
		{
			this.days = days;
			return this;
		}

		public Builder withStartTime(String start_time)
		{
			this.start_time = start_time;
			return this;
		}
		
		public Builder withEndTime(String end_time)
		{
			this.end_time = end_time;
			return this;
		}

		public ModifySectionCalendarDto build()
		{
			return new ModifySectionCalendarDto(this);
		}
	}
}
