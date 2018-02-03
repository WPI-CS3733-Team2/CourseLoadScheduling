package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Section;

public class SectionsExtractor extends Extractor<List<Section>>
{
	@Override
	public List<Section> extractData(ResultSet rs) throws SQLException
	{
		List<Section> resultList = new ArrayList<>();

		while(rs.next())
		{
			Section result = new Section();
				
			result.setId(rs.getInt(Section.getColumnName(Section.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setName(rs.getString(Section.getColumnName(Section.Columns.NAME)));						//
			result.setType(rs.getString(Section.getColumnName(Section.Columns.TYPE)));
			result.setCrn(rs.getInt(Section.getColumnName(Section.Columns.CRN)));
			result.setExpectedPopulation(rs.getInt(Section.getColumnName(Section.Columns.EXPECTED_POPULATION)));
			result.setCourseId(rs.getInt(Section.getColumnName(Section.Columns.COURSE_ID)));
			result.setCalendarId(rs.getInt(Section.getColumnName(Section.Columns.CALENDAR_ID)));
			result.setScheduleId(rs.getInt(Section.getColumnName(Section.Columns.SCHEDULE_ID)));
			result.setCreatedAt(rs.getTimestamp(Section.getColumnName(Section.Columns.CREATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
