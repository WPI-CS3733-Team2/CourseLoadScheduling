package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RequestState extends Model{

	// table name
	public static final String TABLE_NAME = "request_state";
	
	public static enum Columns
	{
		ID,
		STATE,
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
			COLUMN_TYPE_MAP.put(Columns.STATE, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		};
		
		// attributes
		
		private Integer id;
		private String state;
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
		
		public String getState()
		{
			return state;
		}

		public void setState(String state)
		{
			this.state = state;
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
			result = prime * result + ((state == null) ? 0 : state.hashCode());
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
			if (!(obj instanceof RequestState))
			{
				return false;
			}
			RequestState other = (RequestState) obj;
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
			if (state == null)
			{
				if (other.state != null)
				{
					return false;
				}
			}
			else if (!state.equals(other.state))
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
			return true;
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("RequestState [id=");
			builder.append(id);
			builder.append(", state=");
			builder.append(state);
			builder.append("]");
			return builder.toString();
		}
}
