package org.dselent.scheduling.server.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.UserFacultyAssociation;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class UserFacultyAssociationDaoTest {

	@Autowired
	private UserFacultyAssociationDao ufaDao;
	
	@Test
	public void testUfaDao() throws SQLException{
		UserFacultyAssociation ufa = new UserFacultyAssociation();
		ufa.setFacultyId(1);
		ufa.setUserId(1);
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.USER_ID));
		insertColumnNameList.add(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.FACULTY_ID));
		
		keyHolderColumnNameList.add(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.ID));
		keyHolderColumnNameList.add(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.CREATED_AT));
		
		ufaDao.insert(ufa, insertColumnNameList, keyHolderColumnNameList);
	}
}
