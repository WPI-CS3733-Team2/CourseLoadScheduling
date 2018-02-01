package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.User;
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
public class UsersDaoTest
{
	@Autowired
	private UsersDao usersDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void testUsersDao() throws SQLException
    {
    	// INSERT
    	
    	/*User user1 = new User();
    	user1.setWpi_id("?????????x");
    	user1.setUserName("user1x");
    	user1.setFirstName("userx");
    	user1.setLastName("onex");
    	user1.setEmail("useronex@wpi.edu");
    	user1.setEncryptedPassword("11111111x"); // simplified for now
    	user1.setSalt("11111111x"); // also simplified for now
    	user1.setAccountState("1x");; // assumes 1 = activated
    	user1.setDeleted(false);
    	
    	List<String> insertColumnNameList = new ArrayList<>();
    	List<String> keyHolderColumnNameList = new ArrayList<>();
    	
    	insertColumnNameList.add(User.getColumnName(User.Columns.WPI_ID));
    	insertColumnNameList.add(User.getColumnName(User.Columns.USER_NAME));
    	insertColumnNameList.add(User.getColumnName(User.Columns.FIRST_NAME));
    	insertColumnNameList.add(User.getColumnName(User.Columns.LAST_NAME));
    	insertColumnNameList.add(User.getColumnName(User.Columns.EMAIL));
    	insertColumnNameList.add(User.getColumnName(User.Columns.ENCRYPTED_PASSWORD));
    	insertColumnNameList.add(User.getColumnName(User.Columns.SALT));
    	insertColumnNameList.add(User.getColumnName(User.Columns.ACCOUNT_STATE));
    	insertColumnNameList.add(User.getColumnName(User.Columns.DELETED));
    	
    	keyHolderColumnNameList.add(User.getColumnName(User.Columns.ID));
    	keyHolderColumnNameList.add(User.getColumnName(User.Columns.CREATED_AT));
    	keyHolderColumnNameList.add(User.getColumnName(User.Columns.UPDATED_AT));
   	
    	usersDao.insert(user1, insertColumnNameList, keyHolderColumnNameList);*/
    	
    	
    	// UPDATE
    	
    	String updateColumnName = User.getColumnName(User.Columns.USER_NAME);
    	String oldUserName = "user1xxxx";
    	String newUserName = "newUserNamexxxxx";
    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
    	QueryTerm updateUseNameTerm = new QueryTerm();
    	updateUseNameTerm.setColumnName(updateColumnName);
    	updateUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateUseNameTerm.setValue(oldUserName);
    	updateQueryTermList.add(updateUseNameTerm);
    	
    	usersDao.update(updateColumnName, newUserName, updateQueryTermList);
    	
    	
//    	// SELECT
//    	// by user name
//    	
//    	String selectColumnName = User.getColumnName(User.Columns.USER_NAME);
//    	String selectUserName = newUserName;
//    	
//    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
//    	
//    	QueryTerm selectUseNameTerm = new QueryTerm();
//    	selectUseNameTerm.setColumnName(selectColumnName);
//    	selectUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
//    	selectUseNameTerm.setValue(selectUserName);
//    	selectQueryTermList.add(selectUseNameTerm);
//    	
//    	List<String> selectColumnNameList = User.getColumnNameList();
//    	
//    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
//    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
//    	orderByList.add(orderPair1);
//    	
//		@SuppressWarnings("unused")
//		List<User> selectedUserList = usersDao.select(selectColumnNameList, selectQueryTermList, orderByList);
//    	
//    	System.out.println();
    }
}
