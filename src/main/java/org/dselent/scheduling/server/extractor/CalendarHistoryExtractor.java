package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CalendarHistory;

public class CalendarHistoryExtractor extends Extractor<List<CalendarHistory>>
{

	@Override
	public List<CalendarHistory> extractData(ResultSet rs) throws SQLException
	{
		List<CalendarHistory> resultList = new ArrayList<>();

		while(rs.next())
		{
			CalendarHistory result = new CalendarHistory();
			
			result.setId(rs.getInt(CalendarHistory.getColumnName(CalendarHistory.Columns.ID)));
		
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setCalendarId(rs.getInt(CalendarHistory.getColumnName(CalendarHistory.Columns.CALENDAR_ID)));
			
			if(rs.wasNull())
			{
				result.setCalendarId(null);
			}
		
			result.setYear(rs.getInt(CalendarHistory.getColumnName(CalendarHistory.Columns.YEAR)));
		
			if(rs.wasNull()) {
				result.setYear(null);
			}
		
			result.setSemester(rs.getString(CalendarHistory.getColumnName(CalendarHistory.Columns.SEMESTER)));
			result.setDays(rs.getString(CalendarHistory.getColumnName(CalendarHistory.Columns.DAYS)));
			result.setStartTime(rs.getString(CalendarHistory.getColumnName(CalendarHistory.Columns.START_TIME)));
			result.setEndTime(rs.getString(CalendarHistory.getColumnName(CalendarHistory.Columns.END_TIME)));

		
			//result.setUserStateId(rs.getInt(User.getColumnName(User.Columns.USER_STATE_ID)));
		
			/*if(rs.wasNull())
			{
				result.setUserStateId(null);
			}*/
		
			result.setCreatedAt(rs.getTimestamp(CalendarHistory.getColumnName(CalendarHistory.Columns.CREATED_AT)));
			//result.setUpdatedAt(rs.getTimestamp(User.getColumnName(User.Columns.UPDATED_AT)));
	
			resultList.add(result);
		}
		
		return resultList;
	}
}