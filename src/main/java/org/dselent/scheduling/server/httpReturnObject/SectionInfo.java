package org.dselent.scheduling.server.httpReturnObject;

public class SectionInfo {
	// attributes	
	private String name;
	private String type;
	private Integer expected_population;
	private Boolean deleted;
	private CalendarInfo calendar;
	
	public SectionInfo(String name, String type, Integer expected_population, Boolean deleted, CalendarInfo calendar) {
		super();
		this.name = name;
		this.type = type;
		this.expected_population = expected_population;
		this.deleted = deleted;
		this.calendar = calendar;
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public CalendarInfo getCalendar() {
		return calendar;
	}

	public void setCalendar(CalendarInfo calendar) {
		this.calendar = calendar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calendar == null) ? 0 : calendar.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((expected_population == null) ? 0 : expected_population.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SectionInfo other = (SectionInfo) obj;
		if (calendar == null) {
			if (other.calendar != null)
				return false;
		} else if (!calendar.equals(other.calendar))
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "SectionInfo [name=" + name + ", type=" + type + ", expected_population=" + expected_population
				+ ", deleted=" + deleted + ", calendar=" + calendar + "]";
	}


}
