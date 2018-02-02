package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class CourseDaoTest {
	@Autowired
	private CoursesDao courseDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void testUsersDao() throws SQLException{
//    		Course course1 = new Course();
//    		course1.setName("Hello, world");
//    		course1.setFrequency(6666666);
//    		course1.setNumber("aloha3733");
//    		
//    	
//    		List<String> insertColumnNameList = new ArrayList<>();
//    		List<String> keyHolderColumnNameList = new ArrayList<>();
//    	
//    		insertColumnNameList.add(Course.getColumnName(Course.Columns.NAME));
//    		insertColumnNameList.add(Course.getColumnName(Course.Columns.FREQUENCY));
//    		insertColumnNameList.add(Course.getColumnName(Course.Columns.NUMBER));
//    		
//    		keyHolderColumnNameList.add(Course.getColumnName(Course.Columns.ID));
//    		keyHolderColumnNameList.add(Course.getColumnName(Course.Columns.CREATED_AT));
//    		keyHolderColumnNameList.add(Course.getColumnName(Course.Columns.UPDATED_AT));
//
//    		courseDao.insert(course1, insertColumnNameList, keyHolderColumnNameList);
    	
//    		String updateColumnName = Course.getColumnName(Course.Columns.FREQUENCY);
//    		Integer frequency = 6666666;
//    		Integer newFrequency = 6;
//    		List<QueryTerm> updateQueryTermList = new ArrayList<>();
//    	
//    		QueryTerm updateFreq = new QueryTerm();
//    		updateFreq.setColumnName(updateColumnName);
//    		updateFreq.setComparisonOperator(ComparisonOperator.EQUAL);
//    		updateFreq.setValue(frequency);
//    		updateQueryTermList.add(updateFreq);
//    	
//    		courseDao.update(updateColumnName, newFrequency, updateQueryTermList);

    	String selectColumnName = Course.getColumnName(Course.Columns.ID);
    Integer	courseId = 1;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectUseNameTerm = new QueryTerm();
    	selectUseNameTerm.setColumnName(selectColumnName);
    	selectUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectUseNameTerm.setValue(courseId);
    	selectQueryTermList.add(selectUseNameTerm);
    	
    	List<String> selectColumnNameList = Course.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		@SuppressWarnings("unused")
		List<Course> selectedUserList = courseDao.select(selectColumnNameList, selectQueryTermList, orderByList);
    	
    	System.out.println(selectedUserList);
    }
}
