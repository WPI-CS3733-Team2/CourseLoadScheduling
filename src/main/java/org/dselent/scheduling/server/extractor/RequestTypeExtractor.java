package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.RequestType;

public class RequestTypeExtractor extends Extractor<List<RequestType>>{
	
	@Override
	public List<RequestType> extractData(ResultSet rs) throws SQLException
	{
		List<RequestType> resultList = new ArrayList<>();

		while(rs.next())
		{
			RequestType result = new RequestType();
				
			result.setId(rs.getInt(RequestType.getColumnName(RequestType.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setId(rs.getInt(RequestType.getColumnName(RequestType.Columns.ID)));
			result.setType(rs.getString(RequestType.getColumnName(RequestType.Columns.TYPE)));
			result.setCreatedAt(rs.getTimestamp(RequestType.getColumnName(RequestType.Columns.CREATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}
}

