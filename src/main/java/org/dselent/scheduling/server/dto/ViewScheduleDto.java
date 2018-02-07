package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

//import org.dselent.scheduling.server.dto.RegisterUserDto.Builder;

public class ViewScheduleDto{
	private final String schedule_id;
	
	@Generated("SparkTools")
	private ViewScheduleDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.schedule_id = builder.schedule_id;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.schedule_id == null) {
			throw new IllegalStateException("schedule_id cannot be null");
		}
	}

	public String getschedule_id() {
		return schedule_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schedule_id == null) ? 0 : schedule_id.hashCode());
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
		if (schedule_id == null) {
			if (other.schedule_id != null)
				return false;
		} else if (!schedule_id.equals(other.schedule_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ViewScheduleDto [schedule_id=" + schedule_id + "]";
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
		private String schedule_id;
		
		private Builder()
		{
		}
		
		public Builder withScheduleId(String schedule_id) {				//
			this.schedule_id = schedule_id;
			return this;
		}

		public ViewScheduleDto build()
		{
			return new ViewScheduleDto(this);
		}
	}
	
}