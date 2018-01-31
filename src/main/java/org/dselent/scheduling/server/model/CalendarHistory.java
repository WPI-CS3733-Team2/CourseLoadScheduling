package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.dselent.scheduling.server.model.User.Columns;


public class CalendarHistory extends Model
{
	// table name
		public static final String TABLE_NAME = "calendar_history";
			
		// column names
		public static enum Columns
		{
			ID,
			CALENDAR_ID,
			YEAR,
			SEMESTER,
			DAYS,
			START_TIME,
			END_TIME,
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
			COLUMN_TYPE_MAP.put(Columns.CALENDAR_ID, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.YEAR, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.SEMESTER, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.DAYS, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.START_TIME, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.END_TIME, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		};
	// attributes
	private Integer id;
	private Integer calendar_id;
	private Integer year;
	private String semester;
	private String days;
	private String start_time;
	private String end_time;
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
	
	public Integer getCalendarId()
	{
		return calendar_id;
	}
	
	public void setCalendarId(Integer calendar_id)
	{
		this.calendar_id = calendar_id;
	}
	
	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public String getSemester()
	{
		return semester;
	}

	public void setSemester(String semester)
	{
		this.semester = semester;
	}

	public String getDays()
	{
		return days;
	}

	public void setDays(String days)
	{
		this.days = days;
	}


	public String getStartTime()
	{
		return start_time;
	}

	public void setStartTime(String start_time)
	{
		this.start_time = start_time;
	}

	public String getEndTime()
	{
		return end_time;
	}

	public void setEndTime(String end_time)
	{
		this.end_time = end_time;
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

/*	public Instant getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt)
	{
		this.updatedAt = updatedAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt)
	{
		if(updatedAt != null)
		{
			this.updatedAt = updatedAt.toInstant();
		}
	}
*/
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((start_time == null) ? 0 : start_time.hashCode());
		result = prime * result + ((end_time == null) ? 0 : end_time.hashCode());
		result = prime * result + ((semester == null) ? 0 : semester.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((calendar_id == null) ? 0: calendar_id.hashCode());
		result = prime * result + ((days == null) ? 0 : days.hashCode());
		//result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		//result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		//result = prime * result + ((userStateId == null) ? 0 : userStateId.hashCode());
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
		if (!(obj instanceof CalendarHistory))
		{
			return false;
		}
		CalendarHistory other = (CalendarHistory) obj;
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
		if (start_time == null)
		{
			if (other.start_time != null)
			{
				return false;
			}
		}
		else if (!start_time.equals(other.start_time))
		{
			return false;
		}
		if (end_time == null)
		{
			if (other.end_time != null)
			{
				return false;
			}
		}
		else if (!end_time.equals(other.end_time))
		{
			return false;
		}
		if (semester == null)
		{
			if (other.semester != null)
			{
				return false;
			}
		}
		else if (!semester.equals(other.semester))
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
		if (calendar_id == null)
		{
			if (other.calendar_id != null)
			{
				return false;
			}
		}
		else if (!calendar_id.equals(other.calendar_id))
		{
			return false;
		}
		if (days == null)
		{
			if (other.days != null)
			{
				return false;
			}
		}
		else if (!days.equals(other.days))
		{
			return false;
		}
		if (year == null)
		{
			if (other.year != null)
			{
				return false;
			}
		}
		else if (!year.equals(other.year))
		{
			return false;
		}
		/*if (updatedAt == null)
		{
			if (other.updatedAt != null)
			{
				return false;
			}
		}
		else if (!updatedAt.equals(other.updatedAt))
		{
			return false;
		}*/
		/*if (userName == null)
		{
			if (other.userName != null)
			{
				return false;
			}
		}
		else if (!userName.equals(other.userName))
		{
			return false;
		}
		if (userStateId == null)
		{
			if (other.userStateId != null)
			{
				return false;
			}
		}
		else if (!userStateId.equals(other.userStateId))
		{
			return false;
		}*/
		return true;
	}
	

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Calendar [id=");
		builder.append(id);
		builder.append(", calendar_id=");
		builder.append(calendar_id);
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
		builder.append(", createdAt=");
		builder.append(createdAt);
		//builder.append(", updatedAt=");
		//builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
	
}