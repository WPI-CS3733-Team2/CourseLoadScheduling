package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseHistory;

public class CoursesHistoryExtractor extends Extractor<List<CourseHistory>>
{
	@Override
	public List<CourseHistory> extractData(ResultSet rs) throws SQLException
	{
		List<CourseHistory> resultList = new ArrayList<>();

		while(rs.next())
		{
			CourseHistory result = new CourseHistory();
				
			result.setId(rs.getInt(CourseHistory.getColumnName(CourseHistory.Columns.ID)));
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setCourseId(rs.getInt(CourseHistory.getColumnName(CourseHistory.Columns.COURSE_ID)));
			if(rs.wasNull())
			{
				result.setCourseId(null);
			}
			
			result.setNumber(rs.getString(CourseHistory.getColumnName(CourseHistory.Columns.NUMBER)));
			if (rs.wasNull())
			{
				result.setNumber(null);
			}
			
			result.setName(rs.getString(CourseHistory.getColumnName(CourseHistory.Columns.NAME)));
			if (rs.wasNull())
			{
				result.setNumber(null);
			}
			
			result.setFrequency(rs.getInt(CourseHistory.getColumnName(CourseHistory.Columns.FREQUENCY)));
			if (rs.wasNull())
			{
				result.setFrequency(null);
			}
			
			result.setCreatedAt(rs.getTimestamp(CourseHistory.getColumnName(CourseHistory.Columns.CREATED_AT)));

			
			resultList.add(result);
		}
			
		return resultList;
	}

}
