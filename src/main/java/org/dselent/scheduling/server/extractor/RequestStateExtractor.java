package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.RequestState;

public class RequestStateExtractor extends Extractor<List<RequestState>>{
	
	@Override
	public List<RequestState> extractData(ResultSet rs) throws SQLException
	{
		List<RequestState> resultList = new ArrayList<>();

		while(rs.next())
		{
			RequestState result = new RequestState();
				
			result.setId(rs.getInt(RequestState.getColumnName(RequestState.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setId(rs.getInt(RequestState.getColumnName(RequestState.Columns.ID)));
			result.setState(rs.getString(RequestState.getColumnName(RequestState.Columns.STATE)));
		
			resultList.add(result);
		}
			
		return resultList;
	}
}
