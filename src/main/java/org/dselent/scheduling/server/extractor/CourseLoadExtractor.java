package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseLoad;

public class CourseLoadExtractor extends Extractor<List<CourseLoad>>
{
	@Override
	public List<CourseLoad> extractData(ResultSet rs) throws SQLException
	{
		List<CourseLoad> resultList = new ArrayList<>();

		while(rs.next())
		{
			CourseLoad result = new CourseLoad();
				
			result.setId(rs.getInt(CourseLoad.getColumnName(CourseLoad.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setType(rs.getString(CourseLoad.getColumnName(CourseLoad.Columns.TYPE)));
			result.setAmount(rs.getInt(CourseLoad.getColumnName(CourseLoad.Columns.AMOUNT)));
			result.setDeleted(rs.getBoolean(CourseLoad.getColumnName(CourseLoad.Columns.DELETED)));
			result.setCreatedAt(rs.getTimestamp(CourseLoad.getColumnName(CourseLoad.Columns.CREATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
