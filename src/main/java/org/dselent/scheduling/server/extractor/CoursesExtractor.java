package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Course;

public class CoursesExtractor extends Extractor<List<Course>>
{
	@Override
	public List<Course> extractData(ResultSet rs) throws SQLException
	{
		List<Course> resultList = new ArrayList<>();

		while(rs.next())
		{
			Course result = new Course();
				
			result.setId(rs.getInt(Course.getColumnName(Course.Columns.ID)));
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setNumber(rs.getString(Course.getColumnName(Course.Columns.NUMBER)));
			if (rs.wasNull())
			{
				result.setNumber(null);
			}
			
			result.setName(rs.getString(Course.getColumnName(Course.Columns.NAME)));
			if (rs.wasNull())
			{
				result.setNumber(null);
			}
			
			result.setFrequency(rs.getInt(Course.getColumnName(Course.Columns.FREQUENCY)));
			
			resultList.add(result);
		}
			
		return resultList;
	}

}