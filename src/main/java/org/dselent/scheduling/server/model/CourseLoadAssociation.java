package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CourseLoadAssociation extends Model
{
	// table name
	public static final String TABLE_NAME = "course_load_association";
		
	// column names
	public static enum Columns
	{
		ID,
		FACULTY_ID,
		COURSE_LOAD_ID,
		DELETED,
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
		COLUMN_TYPE_MAP.put(Columns.FACULTY_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.COURSE_LOAD_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.DELETED, JDBCType.BOOLEAN);
		COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
	};
	
	// attributes
	
	private Integer id;
	private Integer facultyId;
	private Integer courseLoadId;
	private Boolean deleted;
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

	public Integer getFacultyId() {									//
		return facultyId;
	}

	public void setFacultyId(Integer facultyId) {						//
		this.facultyId = facultyId;
	}

	public Integer getCourseLoadId()
	{
		return courseLoadId;
	}

	public void setCourseLoadId(Integer courseLoadId)
	{
		this.courseLoadId = courseLoadId;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
		result = prime * result + ((courseLoadId == null) ? 0 : courseLoadId.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode()); 				//
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
		if (!(obj instanceof CourseLoadAssociation))
		{
			return false;
		}
		CourseLoadAssociation other = (CourseLoadAssociation) obj;
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

		if (facultyId == null)
		{
			if (other.facultyId != null)
			{
				return false;
			}
		}
		else if (!facultyId.equals(other.facultyId))
		{
			return false;
		}
		if (courseLoadId == null)
		{
			if (other.courseLoadId != null)
			{
				return false;
			}
		}
		else if (!courseLoadId.equals(other.courseLoadId))
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
		if(deleted = null) {							//
			if(other.deleted != null) {
				return false;
			}
		}
		else if(!deleted.equals(other.deleted)) {
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
		builder.append(", faculty_id=");				//
		builder.append(facultyId);					//
		builder.append(", course_load_id=");
		builder.append(courseLoadId);
		builder.append(", deleted=");			//
		builder.append(deleted);					//
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append("]");
		return builder.toString();
	}
	
}
