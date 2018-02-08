package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

//import org.dselent.scheduling.server.dto.RegisterUserDto.Builder;

public class ViewScheduleDto{
	private final String schedule_name;
	
	@Generated("SparkTools")
	private ViewScheduleDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.schedule_name = builder.schedule_name;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.schedule_name == null) {
			throw new IllegalStateException("schedule_name cannot be null");
		}
	}

	public String getScheduleName() {
		return schedule_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schedule_name == null) ? 0 : schedule_name.hashCode());
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
		ViewScheduleDto other = (ViewScheduleDto) obj;
		if (schedule_name == null) {
			if (other.schedule_name != null)
				return false;
		} else if (!schedule_name.equals(other.schedule_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ViewScheduleDto [schedule_name=" + schedule_name + "]";
	}
	
    /**
	 * Creates builder to build {@link ViewScheduleDto}.
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
		private String schedule_name;
		
		private Builder()
		{
		}
		
		public Builder withScheduleId(String schedule_name) {				//
			this.schedule_name = schedule_name;
			return this;
		}

		public ViewScheduleDto build()
		{
			return new ViewScheduleDto(this);
		}
	}
	
}