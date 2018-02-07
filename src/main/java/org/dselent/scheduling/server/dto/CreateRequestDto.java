package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

//import org.dselent.scheduling.server.dto.RegisterUserDto.Builder;

public class CreateRequestDto{
	private final String faculty_id;
	private final String request_type;
	//private final String request_state;
	private final String course_id;
	private final String section_id;
	private final String data;
	
	@Generated("SparkTools")
	private CreateRequestDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		this.faculty_id = builder.faculty_id;
		this.request_type = builder.request_type;
		//this.request_state = builder.request_state;
		this.course_id = builder.course_id;
		this.section_id = builder.section_id;
		this.data = builder.data;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		if(this.faculty_id == null) {
			throw new IllegalStateException("faculty_id cannot be null");
		}
		else if(this.request_type == null)
		{
			throw new IllegalStateException("request_type cannot be null");
		}
		/*else if(this.request_state == null)
		{
			throw new IllegalStateException("request_state cannot be null");
		}*/
		else if(this.course_id == null)
		{
			throw new IllegalStateException("course_id cannot be null");
		}
		else if(this.section_id == null)
		{
			throw new IllegalStateException("section_id cannot be null");
		}
		else if(this.data == null)
		{
			throw new IllegalStateException("data cannot be null");
		}
	}
	
	//Setters and getters
	public String getFaculty_id() {
		return faculty_id;
	}
	public String getRequest_type() {
		return request_type;
	}
	/*public String getRequest_state() {
		return request_state;
	}*/
	public String getCourse_id() {
		return course_id;
	}
	public String getSection_id() {
		return section_id;
	}
	public String getData() {
		return data;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course_id == null) ? 0 : course_id.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((faculty_id == null) ? 0 : faculty_id.hashCode());
		//result = prime * result + ((request_state == null) ? 0 : request_state.hashCode());
		result = prime * result + ((request_type == null) ? 0 : request_type.hashCode());
		result = prime * result + ((section_id == null) ? 0 : section_id.hashCode());
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
		CreateRequestDto other = (CreateRequestDto) obj;
		if (course_id == null) {
			if (other.course_id != null)
				return false;
		} else if (!course_id.equals(other.course_id))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (faculty_id == null) {
			if (other.faculty_id != null)
				return false;
		} else if (!faculty_id.equals(other.faculty_id))
			return false;
		/*if (request_state == null) {
			if (other.request_state != null)
				return false;
		} else if (!request_state.equals(other.request_state))
			return false;*/
		if (request_type == null) {
			if (other.request_type != null)
				return false;
		} else if (!request_type.equals(other.request_type))
			return false;
		if (section_id == null) {
			if (other.section_id != null)
				return false;
		} else if (!section_id.equals(other.section_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreateRequestDto [faculty_id=" + faculty_id + ", request_type=" + request_type + ", course_id=" + course_id + ", section_id=" + section_id + ", data=" + data + "]";
	}
	
	/**
	 * Creates builder to build {@link CreateRequestDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link CreateRequestDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String faculty_id;
		private String request_type;
		//private String request_state;
		private String course_id;
		private String section_id;
		private String data;

		private Builder()
		{
		}
		
		public Builder withFacultyId(String faculty_id) {				//
			this.faculty_id = faculty_id;
			return this;
		}
		
		public Builder withRequestType(String request_type)
		{
			this.request_type = request_type;
			return this;
		}

		/*public Builder withRequestState(String request_state)
		{
			this.request_state = request_state;
			return this;
		}*/

		public Builder withCourseId(String course_id)
		{
			this.course_id = course_id;
			return this;
		}

		public Builder withSectionId(String section_id)
		{
			this.section_id = section_id;
			return this;
		}

		public Builder withData(String data)
		{
			this.data = data;
			return this;
		}

		public CreateRequestDto build()
		{
			return new CreateRequestDto(this);
		}
	}

	
}