package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

//import org.dselent.scheduling.server.dto.RegisterUserDto.Builder;

public class ViewRequestHistoryDto{
	private final String faculty_id;
	
	@Generated("SparkTools")
	private ViewRequestHistoryDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.faculty_id = builder.faculty_id;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.faculty_id == null) {
			throw new IllegalStateException("faculty_id cannot be null");
		}
	}

	public String getFaculty_id() {
		return faculty_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((faculty_id == null) ? 0 : faculty_id.hashCode());
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
		ViewRequestHistoryDto other = (ViewRequestHistoryDto) obj;
		if (faculty_id == null) {
			if (other.faculty_id != null)
				return false;
		} else if (!faculty_id.equals(other.faculty_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ViewRequestHistoryDto [faculty_id=" + faculty_id + "]";
	}
	
    /**
	 * Creates builder to build {@link ViewRequestHistoryDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link ViewRequestHistorytDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String faculty_id;
		
		private Builder()
		{
		}
		
		public Builder withFacultyId(String faculty_id) {				//
			this.faculty_id = faculty_id;
			return this;
		}

		public ViewRequestHistoryDto build()
		{
			return new ViewRequestHistoryDto(this);
		}
	}
	
}