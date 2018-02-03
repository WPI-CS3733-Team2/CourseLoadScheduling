package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.RequestDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.Request;
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
public class RequestDaoTest
{
	@Autowired
	private RequestDao requestDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void testRequestDao() throws SQLException
    {
    	// INSERT
    	
    	/*Request request1 = new Request();
    	request1.setFacultyId(1);
    	request1.setType(1);
    	request1.setState(1);
    	request1.setCourse(1);
    	request1.setSection(1);
    	request1.setData("Data string"); // simplified for now
    	
    	List<String> insertColumnNameList = new ArrayList<>();
    	List<String> keyHolderColumnNameList = new ArrayList<>();
    	
    	insertColumnNameList.add(Request.getColumnName(Request.Columns.FACULTY_ID));
    	insertColumnNameList.add(Request.getColumnName(Request.Columns.TYPE));
    	insertColumnNameList.add(Request.getColumnName(Request.Columns.STATE));
    	insertColumnNameList.add(Request.getColumnName(Request.Columns.COURSE));
    	insertColumnNameList.add(Request.getColumnName(Request.Columns.SECTION));
    	insertColumnNameList.add(Request.getColumnName(Request.Columns.DATA));
    	//insertColumnNameList.add(User.getColumnName(User.Columns.SALT));
    	//insertColumnNameList.add(User.getColumnName(User.Columns.ACCOUNT_STATE));
    	//insertColumnNameList.add(User.getColumnName(User.Columns.DELETED));
    	
    	keyHolderColumnNameList.add(Request.getColumnName(Request.Columns.ID));
    	keyHolderColumnNameList.add(Request.getColumnName(Request.Columns.CREATED_AT));
    	keyHolderColumnNameList.add(Request.getColumnName(Request.Columns.UPDATED_AT));
   	
    	requestDao.insert(request1, insertColumnNameList, keyHolderColumnNameList);*/
    	
    	
    	// UPDATE
//    	
//    	String updateColumnName = Request.getColumnName(Request.Columns.DATA);
//    	String oldData = "Data string";
//    	String newData = "New data string";
//    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
//    	QueryTerm updateDataTerm = new QueryTerm();
//    	updateDataTerm.setColumnName(updateColumnName);
//    	updateDataTerm.setComparisonOperator(ComparisonOperator.EQUAL);
//    	updateDataTerm.setValue(oldData);
//    	updateQueryTermList.add(updateDataTerm);
    	
//    	requestDao.update(updateColumnName, newData, updateQueryTermList);
    	
    	
    	// SELECT
    	// by user name
//    	
    	String selectColumnName = Request.getColumnName(Request.Columns.DATA);
    	String selectData = "New data string";
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectDataTerm = new QueryTerm();
    	selectDataTerm.setColumnName(selectColumnName);
    	selectDataTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectDataTerm.setValue(selectData);
    	selectQueryTermList.add(selectDataTerm);
    	
    	List<String> selectColumnNameList = Request.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		@SuppressWarnings("unused")
		List<Request> selectedRequestList = requestDao.select(selectColumnNameList, selectQueryTermList, orderByList);
//    	
//    	System.out.println();
    }
}