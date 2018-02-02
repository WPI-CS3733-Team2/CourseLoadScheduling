package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.UsersHistory;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.dselent.scheduling.server.dao.UsersHistoryDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class UserHistoryDaoTest {
	@Autowired
	private UsersHistoryDao usersHistoryDao;
	
	@Test
	public void testUsersHistoryDao() throws SQLException{
		String selectColumnName = UsersHistory.getColumnName(UsersHistory.Columns.USER_NAME);
		String userName = "cew";
    	
		List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
		QueryTerm selectUseNameTerm = new QueryTerm();
		selectUseNameTerm.setColumnName(selectColumnName);
		selectUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		selectUseNameTerm.setValue(userName);
		selectQueryTermList.add(selectUseNameTerm);
    	
		List<String> selectColumnNameList = UsersHistory.getColumnNameList();
    	
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
		orderByList.add(orderPair1);
		
			@SuppressWarnings("unused")
			List<UsersHistory> selectedUsersHistoryList = usersHistoryDao.select(selectColumnNameList, selectQueryTermList, orderByList);
		System.out.println(selectedUsersHistoryList);
	}
	
}
