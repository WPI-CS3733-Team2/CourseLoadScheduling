package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.CalendarDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Calendar;
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
public class CalendarDaoTest
{
	@Autowired
	private CalendarDao calendarDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void testCalendarDao() throws SQLException
    {
    	// INSERT
    	
    	Calendar calendar1 = new Calendar();
    	calendar1.setYear(2018);
    	calendar1.setSemester("D");
    	calendar1.setDays("MTRF");
    	calendar1.setStartTime("1400");
    	calendar1.setEndTime("1450");
    	//calendar1.setSalt("11111111"); // also simplified for now
    	//calendar1.setUserStateId(1); // assumes 1 = activated
    	
    	List<String> insertColumnNameList = new ArrayList<>();
    	List<String> keyHolderColumnNameList = new ArrayList<>();
    	
    	insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.YEAR));
    	insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.SEMESTER));
    	insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.DAYS));
    	insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.START_TIME));
    	insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.END_TIME));
    	//insertColumnNameList.add(User.getColumnName(User.Columns.SALT));
    	//insertColumnNameList.add(User.getColumnName(User.Columns.USER_STATE_ID));
    	
    	keyHolderColumnNameList.add(Calendar.getColumnName(Calendar.Columns.ID));
    	keyHolderColumnNameList.add(Calendar.getColumnName(Calendar.Columns.CREATED_AT));
    	//keyHolderColumnNameList.add(User.getColumnName(User.Columns.UPDATED_AT));
   	
    	int id = calendarDao.insert(calendar1, insertColumnNameList, keyHolderColumnNameList);
    	System.out.println(id);
    	
    	// UPDATE
    	
    	String updateColumnName = Calendar.getColumnName(Calendar.Columns.DAYS);
    	String oldDays = "MTRF";
    	String newDays = "MR";
    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
    	QueryTerm updateDaysTerm = new QueryTerm();
    	updateDaysTerm.setColumnName(updateColumnName);
    	updateDaysTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateDaysTerm.setValue(oldDays);
    	updateQueryTermList.add(updateDaysTerm);
    	
    	calendarDao.update(updateColumnName, newDays, updateQueryTermList);
    	
    	
    	// SELECT
    	// by days
    	
    	String selectColumnName = Calendar.getColumnName(Calendar.Columns.DAYS);
    	String selectDays = newDays;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectDaysTerm = new QueryTerm();
    	selectDaysTerm.setColumnName(selectColumnName);
    	selectDaysTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectDaysTerm.setValue(selectDays);
    	selectQueryTermList.add(selectDaysTerm);
    	
    	List<String> selectColumnNameList = Calendar.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		@SuppressWarnings("unused")
		List<Calendar> selectedUserList = calendarDao.select(selectColumnNameList, selectQueryTermList, orderByList);
    	
    	System.out.println();
    }
}
