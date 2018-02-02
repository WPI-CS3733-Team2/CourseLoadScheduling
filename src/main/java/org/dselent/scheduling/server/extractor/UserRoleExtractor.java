package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.UserRole;
import org.dselent.scheduling.server.model.UsersHistory;

public class UserRoleExtractor extends Extractor<List<UserRole>> {

	@Override
	public List<UserRole> extractData(ResultSet rs) throws SQLException {
		List<UserRole> resultList = new ArrayList<>();

		while(rs.next())
		{
			UserRole result = new UserRole();
				
			result.setId(rs.getInt(UserRole.getColumnName(UserRole.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setRoleName(rs.getString(UserRole.getColumnName(UserRole.Columns.ROLE_NAME)));			
			result.setDeleted(rs.getBoolean(UsersHistory.getColumnName(UsersHistory.Columns.DELETED)));
			result.setCreatedAt(rs.getTimestamp(UsersHistory.getColumnName(UsersHistory.Columns.CREATED_AT)));
			result.setDeleted(rs.getBoolean(UserRole.getColumnName(UserRole.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
