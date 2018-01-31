package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.UserFacultyAssociation;

public class UserFacultyAssociationExtractor extends Extractor<List<UserFacultyAssociation>> {

	@Override
	public List<UserFacultyAssociation> extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<UserFacultyAssociation> resultList = new ArrayList<>();

		while(rs.next())
		{
			UserFacultyAssociation result = new UserFacultyAssociation();
				
			result.setId(rs.getInt(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setUserId(rs.getInt(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.USER_ID)));
			result.setFacultyId(rs.getInt(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.FACULTY_ID)));
			result.setCreatedAt(rs.getTimestamp(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.CREATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
