package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.RequestDao;
import org.dselent.scheduling.server.dao.RequestTypeDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.Request;
import org.dselent.scheduling.server.model.RequestType;
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
public class RequestTypeDaoTest
{
	@Autowired
	private RequestTypeDao requestTypeDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void testRequestTypeDao() throws SQLException
    {
    	// INSERT
    	
    	/*RequestType requestType1 = new RequestType();
    	requestType1.setType("course");
    	
    	List<String> insertColumnNameList = new ArrayList<>();
    	List<String> keyHolderColumnNameList = new ArrayList<>();
    	
    	insertColumnNameList.add(RequestType.getColumnName(RequestType.Columns.TYPE));
    	
    	keyHolderColumnNameList.add(RequestType.getColumnName(RequestType.Columns.ID));
    	keyHolderColumnNameList.add(RequestType.getColumnName(RequestType.Columns.CREATED_AT));
   	
    	requestTypeDao.insert(requestType1, insertColumnNameList, keyHolderColumnNameList);*/
    	
    	
    	// UPDATE
//    	
//    	String updateColumnName = RequestType.getColumnName(RequestType.Columns.TYPE);
//    	String oldType = "course";
//    	String newType = "class";
//    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
//    	QueryTerm updateTypeTerm = new QueryTerm();
//    	updateTypeTerm.setColumnName(updateColumnName);
//    	updateTypeTerm.setComparisonOperator(ComparisonOperator.EQUAL);
//    	updateTypeTerm.setValue(oldType);
//    	updateQueryTermList.add(updateTypeTerm);
    	
//    	requestTypeDao.update(updateColumnName, newType, updateQueryTermList);
    	
    	
    	// SELECT
    	// by type
//    	
    	String selectColumnName = RequestType.getColumnName(RequestType.Columns.TYPE);
    	String selectType = "class";
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectTypeTerm = new QueryTerm();
    	selectTypeTerm.setColumnName(selectColumnName);
    	selectTypeTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectTypeTerm.setValue(selectType);
    	selectQueryTermList.add(selectTypeTerm);
    	
    	List<String> selectColumnNameList = RequestType.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		@SuppressWarnings("unused")
		List<RequestType> selectedRequestTypeList = requestTypeDao.select(selectColumnNameList, selectQueryTermList, orderByList);
    	
    	System.out.println();
    }
}