package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Faculty;

public class FacultyExtractor extends Extractor<List<Faculty>> {

	@Override
	public List<Faculty> extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Faculty> resultList = new ArrayList<>();

		while(rs.next())
		{
			Faculty result = new Faculty();
				
			result.setId(rs.getInt(Faculty.getColumnName(Faculty.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setRank(rs.getInt(Faculty.getColumnName(Faculty.Columns.RANK)));
			result.setAssigned(rs.getBoolean(Faculty.getColumnName(Faculty.Columns.ASSIGNED)));
			result.setDeleted(rs.getBoolean(Faculty.getColumnName(Faculty.Columns.DELETED)));
			result.setCreatedAt(rs.getTimestamp(Faculty.getColumnName(Faculty.Columns.CREATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
