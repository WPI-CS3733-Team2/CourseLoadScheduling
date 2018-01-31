package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseLoadAssociation;

public class CourseLoadAssociationExtractor extends Extractor<List<CourseLoadAssociation>>
{
	@Override
	public List<CourseLoadAssociation> extractData(ResultSet rs) throws SQLException
	{
		List<CourseLoadAssociation> resultList = new ArrayList<>();

		while(rs.next())
		{
			CourseLoadAssociation result = new CourseLoadAssociation();
				
			result.setId(rs.getInt(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setFacultyId(rs.getInt(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID)));						//
			result.setCourseLoadId(rs.getInt(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.COURSE_LOAD_ID)));
			result.setDeleted(rs.getBoolean(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.DELETED)));
			result.setCreatedAt(rs.getTimestamp(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.CREATED_AT)));
			resultList.add(result);
		}
			
		return resultList;
	}

}
