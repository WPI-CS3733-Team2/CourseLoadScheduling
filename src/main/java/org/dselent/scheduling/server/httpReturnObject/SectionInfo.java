package org.dselent.scheduling.server.httpReturnObject;

public class SectionInfo {
	// attributes	
	private Integer id;
	private String name;
	private String type;
	private Integer expected_population;
	private CalendarInfo calendar;
	private Integer crn;
	private Integer courseId;
	private Integer calendarId;
	private Integer scheduleId;
	
	public SectionInfo(Integer id, String name, String type, Integer expected_population, CalendarInfo calendar,
			Integer crn, Integer courseId, Integer calendarId, Integer scheduleId) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.expected_population = expected_population;
		this.calendar = calendar;
		this.crn = crn;
		this.courseId = courseId;
		this.calendarId = calendarId;
		this.scheduleId = scheduleId;
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

	public CalendarInfo getCalendar() {
		return calendar;
	}

	public void setCalendar(CalendarInfo calendar) {
		this.calendar = calendar;
	}

	public Integer getCrn() {
		return crn;
	}

	public void setCrn(Integer crn) {
		this.crn = crn;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calendar == null) ? 0 : calendar.hashCode());
		result = prime * result + ((calendarId == null) ? 0 : calendarId.hashCode());
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((crn == null) ? 0 : crn.hashCode());
		result = prime * result + ((expected_population == null) ? 0 : expected_population.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((scheduleId == null) ? 0 : scheduleId.hashCode());
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
		if (calendarId == null) {
			if (other.calendarId != null)
				return false;
		} else if (!calendarId.equals(other.calendarId))
			return false;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (crn == null) {
			if (other.crn != null)
				return false;
		} else if (!crn.equals(other.crn))
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
		if (scheduleId == null) {
			if (other.scheduleId != null)
				return false;
		} else if (!scheduleId.equals(other.scheduleId))
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
		return "SectionInfo [id=" + id + ", name=" + name + ", type=" + type + ", expected_population="
				+ expected_population + ", calendar=" + calendar + ", crn=" + crn + ", courseId=" + courseId
				+ ", calendarId=" + calendarId + ", scheduleId=" + scheduleId + "]";
	}
	
}
