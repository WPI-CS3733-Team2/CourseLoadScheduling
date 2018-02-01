package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Request;

public class RequestExtractor extends Extractor<List<Request>>{
	
	@Override
	public List<Request> extractData(ResultSet rs) throws SQLException
	{
		List<Request> resultList = new ArrayList<>();

		while(rs.next())
		{
			Request result = new Request();
				
			result.setId(rs.getInt(Request.getColumnName(Request.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setFacultyId(rs.getInt(Request.getColumnName(Request.Columns.FACULTY_ID)));
			result.setType(rs.getInt(Request.getColumnName(Request.Columns.TYPE)));
			result.setState(rs.getInt(Request.getColumnName(Request.Columns.STATE)));
			result.setCourse(rs.getInt(Request.getColumnName(Request.Columns.COURSE)));
			result.setSection(rs.getInt(Request.getColumnName(Request.Columns.SECTION)));
			result.setData(rs.getString(Request.getColumnName(Request.Columns.DATA)));
			result.setCreatedAt(rs.getTimestamp(Request.getColumnName(Request.Columns.CREATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}
}
