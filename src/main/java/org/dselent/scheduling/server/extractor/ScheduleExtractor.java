package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.User;

public class ScheduleExtractor extends Extractor<List<Schedule>>
{
	@Override
	public List<Schedule> extractData(ResultSet rs) throws SQLException
	{
		List<Schedule> resultList = new ArrayList<>();

		while(rs.next())
		{
			Schedule result = new Schedule();
				
			result.setId(rs.getInt(Schedule.getColumnName(Schedule.Columns.ID)));
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setFacultyId(rs.getInt(Schedule.getColumnName(Schedule.Columns.FACULTY_ID)));
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setScheduleName(rs.getString(Schedule.getColumnName(Schedule.Columns.SCHEDULE_NAME)));
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setCreatedAt(rs.getTimestamp(Schedule.getColumnName(Schedule.Columns.CREATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
