package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Calendar;

public class CalendarExtractor extends Extractor<List<Calendar>>
{

	@Override
	public List<Calendar> extractData(ResultSet rs) throws SQLException
	{
		List<Calendar> resultList = new ArrayList<>();

		while(rs.next())
		{
			Calendar result = new Calendar();
			
			result.setId(rs.getInt(Calendar.getColumnName(Calendar.Columns.ID)));
		
			if(rs.wasNull())
			{
				result.setId(null);
			}
		
			result.setYear(rs.getInt(Calendar.getColumnName(Calendar.Columns.YEAR)));
		
			if(rs.wasNull()) {
				result.setYear(null);
			}
		
			result.setSemester(rs.getString(Calendar.getColumnName(Calendar.Columns.SEMESTER)));
			result.setDays(rs.getString(Calendar.getColumnName(Calendar.Columns.DAYS)));
			result.setStartTime(rs.getString(Calendar.getColumnName(Calendar.Columns.START_TIME)));
			result.setEndTime(rs.getString(Calendar.getColumnName(Calendar.Columns.END_TIME)));
			//result.setSalt(rs.getString(User.getColumnName(User.Columns.SALT)));
		
			//result.setUserStateId(rs.getInt(User.getColumnName(User.Columns.USER_STATE_ID)));
		
			/*if(rs.wasNull())
			{
				result.setUserStateId(null);
			}*/
		
			result.setCreatedAt(rs.getTimestamp(Calendar.getColumnName(Calendar.Columns.CREATED_AT)));
			//result.setUpdatedAt(rs.getTimestamp(User.getColumnName(User.Columns.UPDATED_AT)));
	
			resultList.add(result);
		}
		
		return resultList;
	}
}