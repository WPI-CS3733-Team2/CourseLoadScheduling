package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.RequestDao;
import org.dselent.scheduling.server.dao.RequestTypeDao;
import org.dselent.scheduling.server.dao.RequestStateDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.Request;
import org.dselent.scheduling.server.model.RequestType;
import org.dselent.scheduling.server.model.RequestState;
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
public class RequestStateDaoTest
{
	@Autowired
	private RequestStateDao requestStateDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void testRequestStateDao() throws SQLException
    {
    	// INSERT
    	
    	/*RequestState requestState1 = new RequestState();
    	requestState1.setState("approved");
    	
    	List<String> insertColumnNameList = new ArrayList<>();
    	List<String> keyHolderColumnNameList = new ArrayList<>();
    	
    	insertColumnNameList.add(RequestState.getColumnName(RequestState.Columns.STATE));
    	
    	keyHolderColumnNameList.add(RequestState.getColumnName(RequestState.Columns.ID));
    	keyHolderColumnNameList.add(RequestState.getColumnName(RequestState.Columns.CREATED_AT));
   	
    	requestStateDao.insert(requestState1, insertColumnNameList, keyHolderColumnNameList);*/
    	
    	
    	// UPDATE
//    	
//    	String updateColumnName = RequestState.getColumnName(RequestState.Columns.STATE);
//    	String oldState = "approved";
//    	String newState = "accepted";
//   	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
//    	QueryTerm updateStateTerm = new QueryTerm();
//    	updateStateTerm.setColumnName(updateColumnName);
//   	updateStateTerm.setComparisonOperator(ComparisonOperator.EQUAL);

//    	updateStateTerm.setValue(oldState);
//    	updateQueryTermList.add(updateStateTerm);
    	
//    	requestStateDao.update(updateColumnName, newState, updateQueryTermList);
    	
//    	
    	// SELECT
    	// by state
//    	
    	String selectColumnName = RequestState.getColumnName(RequestState.Columns.STATE);
    	String selectState = "accepted";
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectStateTerm = new QueryTerm();
    	selectStateTerm.setColumnName(selectColumnName);
    	selectStateTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectStateTerm.setValue(selectState);
    	selectQueryTermList.add(selectStateTerm);
    	
    	List<String> selectColumnNameList = RequestState.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		@SuppressWarnings("unused")
		List<RequestState> selectedRequestStateList = requestStateDao.select(selectColumnNameList, selectQueryTermList, orderByList);
    	
    	System.out.println();
    }
}