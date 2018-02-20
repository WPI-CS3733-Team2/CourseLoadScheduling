package org.dselent.scheduling.server.httpReturnObject;

public class Section {
	// attributes	
	private Integer id;
	private String name;
	private String type;
	private Integer expected_population;
	private Integer course_id;
	private Integer calendar_id;
	private Integer schedule_id;
	private Boolean deleted;

	// methods

	public Section(int id, String name, String type, Integer expected_population, Integer course_id, Integer calendar_id, Integer schedule_id, Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.expected_population = expected_population;
		this.course_id = course_id;
		this.calendar_id = calendar_id;
		this.schedule_id = schedule_id;
		this.deleted = deleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getExpected_population() {
		return expected_population;
	}

	public void setExpected_population(Integer expected_population) {
		this.expected_population = expected_population;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public Integer getCalendar_id() {
		return calendar_id;
	}

	public void setCalendar_id(Integer calendar_id) {
		this.calendar_id = calendar_id;
	}

	public Integer getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(Integer schedule_id) {
		this.schedule_id = schedule_id;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calendar_id == null) ? 0 : calendar_id.hashCode());
		result = prime * result + ((course_id == null) ? 0 : course_id.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((expected_population == null) ? 0 : expected_population.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((schedule_id == null) ? 0 : schedule_id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Section other = (Section) obj;
		if (calendar_id == null) {
			if (other.calendar_id != null)
				return false;
		} else if (!calendar_id.equals(other.calendar_id))
			return false;
		if (course_id == null) {
			if (other.course_id != null)
				return false;
		} else if (!course_id.equals(other.course_id))
			return false;
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (expected_population == null) {
			if (other.expected_population != null)
				return false;
		} else if (!expected_population.equals(other.expected_population))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (schedule_id == null) {
			if (other.schedule_id != null)
				return false;
		} else if (!schedule_id.equals(other.schedule_id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", name=" + name + ", type=" + type + ", expected_population="
				+ expected_population + ", course_id=" + course_id + ", calendar_id=" + calendar_id + ", schedule_id="
				+ schedule_id + ", deleted=" + deleted + "]";
	}


}
