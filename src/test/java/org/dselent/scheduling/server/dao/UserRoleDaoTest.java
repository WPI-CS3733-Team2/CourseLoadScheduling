package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.model.UserRole;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class UserRoleDaoTest {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Test
	public void testUserRoleDao() throws SQLException{
//		UserRole role1 = new UserRole();
//		role1.setRoleName("CS TA");
//		
//		List<String> insertColumnNameList = new ArrayList<>();
//		List<String> keyHolderColumnNameList = new ArrayList<>();
//		
//		insertColumnNameList.add(UserRole.getColumnName(UserRole.Columns.ROLE_NAME));
//		
//		keyHolderColumnNameList.add(UserRole.getColumnName(UserRole.Columns.ID));
//		keyHolderColumnNameList.add(UserRole.getColumnName(UserRole.Columns.DELETED));
//		keyHolderColumnNameList.add(UserRole.getColumnName(UserRole.Columns.CREATED_AT));
//		//keyHolderColumnNameList.add(UserRole.getColumnName(UserRole.Columns.UPDATED_AT));
//		
//		userRoleDao.insert(role1, insertColumnNameList, keyHolderColumnNameList);
		
		String updateColumnName = UserRole.getColumnName(UserRole.Columns.DELETED);
		Boolean oldValue = false;
		Boolean newValue = true;
		List<QueryTerm> updateQueryTermList = new ArrayList<>();

		QueryTerm updateUseNameTerm = new QueryTerm();
		updateUseNameTerm.setColumnName(updateColumnName);
		updateUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateUseNameTerm.setValue(oldValue);
		updateQueryTermList.add(updateUseNameTerm);

		userRoleDao.update(updateColumnName, newValue, updateQueryTermList);
		
	}
	
}
