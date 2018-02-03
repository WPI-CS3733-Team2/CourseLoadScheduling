package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Request extends Model {
	
	// table name
	public static final String TABLE_NAME = "requests";
	
	public static enum Columns
	{
		ID,
		FACULTY_ID,
		TYPE,
		STATE,
		COURSE,
		SECTION,
		DATA,
		CREATED_AT,
		UPDATED_AT
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
			COLUMN_TYPE_MAP.put(Columns.TYPE, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.STATE, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.COURSE, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.SECTION, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.DATA, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
			COLUMN_TYPE_MAP.put(Columns.UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		};
		
		// attributes
		
		private Integer id;
		private Integer facultyId;
		private Integer type;
		private Integer state;
		private Integer course;
		private Integer section;
		private String data;
		private Instant createdAt;
		private Instant updatedAt;


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
		
		// The following functions were written by Jinwei.
		
		public Integer getId()
		{
			return id;
		}

		public void setId(Integer id)
		{
			this.id = id;
		}
		
		public Integer getFacultyId()
		{
			return facultyId;
		}

		public void setFacultyId(Integer facultyId)
		{
			this.facultyId = facultyId;
		}
		
		public Integer getType()
		{
			return type;
		}

		public void setType(Integer type)
		{
			this.type = type;
		}
		
		public Integer getState()
		{
			return state;
		}

		public void setState(Integer state)
		{
			this.state = state;
		}
		
		public Integer getCourse()
		{
			return course;
		}

		public void setCourse(Integer course)
		{
			this.course = course;
		}
		
		public Integer getSection()
		{
			return section;
		}

		public void setSection(Integer section)
		{
			this.section = section;
		}
		
		public String getData()
		{
			return data;
		}

		public void setData(String data)
		{
			this.data = data;
		}
		//////////////////
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

		public Instant getUpdatedAt()
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
		
		
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			result = prime * result + ((section == null) ? 0 : section.hashCode());
			result = prime * result + ((course == null) ? 0 : course.hashCode());
			result = prime * result + ((state == null) ? 0 : state.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			if (!(obj instanceof Request))
			{
				return false;
			}
			Request other = (Request) obj;
			//**********
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
			//**********
			if(id == null)
			{
				if(other.id != null)
				{
					return false;
				}
			}
			else if(!id.equals(other.id))
			{
				return false;
			}
			//**********
			if(facultyId == null)
			{
				if(other.facultyId != null)
				{
					return false;
				}
			}
			else if(!facultyId.equals(other.facultyId))
			{
				return false;
			}
			//**********
			if(type == null)
			{
				if(other.type != null)
				{
					return false;
				}
			}
			else if(!type.equals(other.type))
			{
				return false;
			}
			//**********
			if(state == null)
			{
				if(other.state != null)
				{
					return false;
				}
			}
			else if(!state.equals(other.state))
			{
				return false;
			}
			//**********
			if(course == null)
			{
				if(other.course != null)
				{
					return false;
				}
			}
			else if(!course.equals(other.course))
			{
				return false;
			}
			//**********
			if(section == null)
			{
				if(other.section != null)
				{
					return false;
				}
			}
			else if(!section.equals(other.section))
			{
				return false;
			}
			//**********
			if(data == null)
			{
				if(other.data != null)
				{
					return false;
				}
			}
			else if(!data.equals(other.data))
			{
				return false;
			}
			//**********
			return true;
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("Request [id=");
			builder.append(id);
			builder.append(", facultyId=");
			builder.append(facultyId);
			builder.append(", type=");
			builder.append(type);
			builder.append(", state=");
			builder.append(state);
			builder.append(", course=");
			builder.append(course);
			builder.append(", section=");
			builder.append(section);
			builder.append(", data=");
			builder.append(data);
			builder.append("]");
			return builder.toString();
		}
			
}
