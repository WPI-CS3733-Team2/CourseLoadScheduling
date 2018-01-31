package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Section extends Model
{
	// table name
	public static final String TABLE_NAME = "sections";
		
	// column names
	public static enum Columns
	{
		ID,
		CRN,
		NAME,
		TYPE,
		EXPECTED_POPULATION,
		COURSE_ID,
		CALENDAR_ID,
		SCHEDULE_ID,
		CREATED_AT
	}
	
	// enum list
	private static final List<Columns> COLUMN_LIST = new ArrayList<>();
	
	// type mapping
	private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();
	
	static
	{
		for(Columns key : Columns.values())
		{
			COLUMN_LIST.add(key);
		}
		
		COLUMN_TYPE_MAP.put(Columns.ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.CRN, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.TYPE, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.EXPECTED_POPULATION, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.COURSE_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.CALENDAR_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.SCHEDULE_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
	};
	
	// attributes
	
	private Integer id;
	private String crn;
	private String name;
	private String type;
	private String expectedPopulation;
	private String courseId;
	private String calendarId;
	private String scheduleId;
	private Instant createdAt;

	// methods
		
	public static JDBCType getColumnType(Columns column)
	{
		return COLUMN_TYPE_MAP.get(column);
	}
	
	public static String getColumnName(Columns column)
	{
		return column.toString().toLowerCase();
	}
	
	public static List<String> getColumnNameList()
	{
		List<String> columnNameList = new ArrayList<>();
		
		for(Columns column : COLUMN_LIST)
		{
			columnNameList.add(getColumnName(column));
		}
		
		return columnNameList;
	}
	
	//
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getCRN() {									
		return crn;
	}

	public void setCRN(String crn) {						
		this.crn = crn;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpectedPopulation() {
		return expectedPopulation;
	}

	public void setExpectedPopulation(String expectedPopulation) {
		this.expectedPopulation = expectedPopulation;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Instant getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt)
	{
		this.createdAt = createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt)
	{
		if(createdAt != null)
		{
			this.createdAt = createdAt.toInstant();
		}
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((crn == null) ? 0 : crn.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((expectedPopulation == null) ? 0 : expectedPopulation.hashCode());
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((calendarId == null) ? 0 : calendarId.hashCode());
		result = prime * result + ((scheduleId == null) ? 0 : scheduleId.hashCode());
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
		if (!(obj instanceof Section))
		{
			return false;
		}
		Section other = (Section) obj;
		if (createdAt == null)
		{
			if (other.createdAt != null)
			{
				return false;
			}
		}
		else if (!createdAt.equals(other.createdAt))
		{
			return false;
		}
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
		if (name == null)
		{
			if (other.name != null)
			{
				return false;
			}
		}
		else if (!name.equals(other.name))
		{
			return false;
		}
		if (type == null)
		{
			if (other.type != null)
			{
				return false;
			}
		}
		else if (!type.equals(other.type))
		{
			return false;
		}
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		}
		else if (!id.equals(other.id))
		{
			return false;
		}
		if (expectedPopulation == null)
		{
			if (other.expectedPopulation != null)
			{
				return false;
			}
		}
		else if (!expectedPopulation.equals(other.expectedPopulation))
		{
			return false;
		}
		if (courseId == null)
		{
			if (other.courseId != null)
			{
				return false;
			}
		}
		else if (!courseId.equals(other.courseId))
		{
			return false;
		}
		if (calendarId == null)
		{
			if (other.calendarId != null)
			{
				return false;
			}
		}
		else if (!calendarId.equals(other.calendarId))
		{
			return false;
		}
		if (scheduleId == null)
		{
			if (other.scheduleId != null)
			{
				return false;
			}
		}
		else if (!scheduleId.equals(other.scheduleId))
		{
			return false;
		}
		return true;
	}
	

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Users [id=");
		builder.append(id);
		builder.append(", crn=");				
		builder.append(crn);					
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", expectedPopulation=");
		builder.append(expectedPopulation);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", calendarId=");
		builder.append(calendarId);
		builder.append(", scheduleId=");
		builder.append(scheduleId);			
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append("]");
		return builder.toString();
	}
	
}
