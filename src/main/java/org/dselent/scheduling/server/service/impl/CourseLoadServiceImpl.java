package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CourseLoadDao;
import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.service.CourseLoadService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseLoadServiceImpl implements CourseLoadService
{
	@Autowired
	private CourseLoadDao courseLoadDao;
	
    public CourseLoadServiceImpl()
    {
    	//
    }

//	@Override
//	public List<Integer> delete(int id) throws SQLException {
//		String updateColumnName = UsersRolesLink.getColumnName(UsersRolesLink.Columns.DELETED);
//		
//		List<QueryTerm> updateQueryTermList = new ArrayList<>();
//		QueryTerm updateDeletedTerm = new QueryTerm();
//		
//		updateDeletedTerm.setColumnName(UsersRolesLink.getColumnName(UsersRolesLink.Columns.USER_ID));
//		updateDeletedTerm.setComparisonOperator(ComparisonOperator.EQUAL);
//		updateDeletedTerm.setValue(userId);
//		updateQueryTermList.add(updateDeletedTerm);
//
//		int result = usersRolesLinksDao.update(updateColumnName, true, updateQueryTermList);
//		return result;
//	}
    
}
