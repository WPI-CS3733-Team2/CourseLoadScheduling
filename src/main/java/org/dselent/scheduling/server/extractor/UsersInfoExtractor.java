package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.custom.UserInfo;

public class UsersInfoExtractor extends Extractor<List<UserInfo>>
{
	@Override
	public List<UserInfo> extractData(ResultSet rs) throws SQLException
	{
		List<UserInfo> resultList = new ArrayList<>();

		while(rs.next())
		{
			UserInfo result = new UserInfo();
				
			result.setUsersId(rs.getInt(UserInfo.getColumnName(UserInfo.Columns.USERS_ID)));
			
			if(rs.wasNull())
			{
				result.setUsersId(null);
			}
			
			result.setUsersWpiId(rs.getString(UserInfo.getColumnName(UserInfo.Columns.USERS_WPI_ID)));
			result.setUsersUserName(rs.getString(UserInfo.getColumnName(UserInfo.Columns.USERS_USER_NAME)));
			result.setUsersFirstName(rs.getString(UserInfo.getColumnName(UserInfo.Columns.USERS_FIRST_NAME)));
			result.setUsersLastName(rs.getString(UserInfo.getColumnName(UserInfo.Columns.USERS_LAST_NAME)));
			result.setUsersEmail(rs.getString(UserInfo.getColumnName(UserInfo.Columns.USERS_EMAIL)));
			result.setUsersEncryptedPassword(rs.getString(UserInfo.getColumnName(UserInfo.Columns.USERS_ENCRYPTED_PASSWORD)));
			result.setUsersSalt(rs.getString(UserInfo.getColumnName(UserInfo.Columns.USERS_SALT)));
			result.setUsersAccountState(rs.getString(UserInfo.getColumnName(UserInfo.Columns.USERS_ACCOUNT_STATE)));
			result.setUsersCreatedAt(rs.getTimestamp(UserInfo.getColumnName(UserInfo.Columns.USERS_CREATED_AT)));
			result.setUsersUpdatedAt(rs.getTimestamp(UserInfo.getColumnName(UserInfo.Columns.USERS_UPDATED_AT)));
			result.setUsersDeleted(rs.getBoolean(UserInfo.getColumnName(UserInfo.Columns.USERS_DELETED)));
			
			if(rs.wasNull())
			{
				result.setUsersDeleted(null);
			}
			
			result.setFacultyId(rs.getInt(UserInfo.getColumnName(UserInfo.Columns.FACULTY_ID)));
			
			if(rs.wasNull())
			{
				result.setFacultyId(null);
			}
			
			result.setUserRolesId(rs.getInt(UserInfo.getColumnName(UserInfo.Columns.USER_ROLES_ID)));
			
			if(rs.wasNull())
			{
				result.setUserRolesId(null);
			}
			
			result.setUserRolesRoleName(rs.getString(UserInfo.getColumnName(UserInfo.Columns.USER_ROLES_ROLE_NAME)));
		
			if(rs.wasNull())
			{
				result.setUserRolesRoleName(null);
			}
			
			resultList.add(result);
		}

		return resultList;
	}

}
