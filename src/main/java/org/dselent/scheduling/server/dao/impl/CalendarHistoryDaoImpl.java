package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CalendarHistoryDao;
import org.dselent.scheduling.server.extractor.CalendarHistoryExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CalendarHistory;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


/*
 * @Repository annotation
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Repository.html
 * 
 * Useful link
 * https://howtodoinjava.com/spring/spring-core/how-to-use-spring-component-repository-service-and-controller-annotations/
 */
@Repository
public class CalendarHistoryDaoImpl extends BaseDaoImpl<CalendarHistory> implements CalendarHistoryDao
{
	@Override
	public int insert(CalendarHistory calendarHistoryModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(CalendarHistory.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, calendarHistoryModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, calendarHistoryModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<CalendarHistory> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		CalendarHistoryExtractor extractor = new CalendarHistoryExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(CalendarHistory.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<CalendarHistory> calendarHistoryList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return calendarHistoryList;
	}

	@Override
	public CalendarHistory findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(CalendarHistory.getColumnName(CalendarHistory.Columns.ID), false);
		List<String> selectColumnNames = CalendarHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<CalendarHistory> calendarHistoryList = select(selectColumnNames, queryTermList, orderByList);
	
	    CalendarHistory calendarHistory = null;
	    
	    if(!calendarHistoryList.isEmpty())
	    {
	    	calendarHistory = calendarHistoryList.get(0);
	    }
	    
	    return calendarHistory;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(CalendarHistory.TABLE_NAME, columnName, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		objectList.add(newValue);
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}
	
	@Override
	public int delete(List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateDeleteString(CalendarHistory.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CalendarHistory calendarHistoryModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.ID)))
    	{
    		parameters.addValue(parameterName, calendarHistoryModel.getId());
    	}
    	else if(insertColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.CALENDAR_ID)))
    	{
    		parameters.addValue(parameterName, calendarHistoryModel.getCalendarId());
    	}
    	else if(insertColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.YEAR)))
    	{
    		parameters.addValue(parameterName, calendarHistoryModel.getYear());
    	}
    	else if(insertColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.SEMESTER)))
    	{
    		parameters.addValue(parameterName, calendarHistoryModel.getSemester());
    	}
    	else if(insertColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.DAYS)))
    	{
    		parameters.addValue(parameterName, calendarHistoryModel.getDays());
    	}
    	else if(insertColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.START_TIME)))
    	{
    		parameters.addValue(parameterName, calendarHistoryModel.getStartTime());
    	}
    	else if(insertColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.END_TIME)))
    	{
    		parameters.addValue(parameterName, calendarHistoryModel.getEndTime());
    	}
    	/*else if(insertColumnName.equals(User.getColumnName(User.Columns.SALT)))
    	{
    		parameters.addValue(parameterName, userModel.getSalt());
    	}
    	else if(insertColumnName.equals(User.getColumnName(User.Columns.USER_STATE_ID)))
    	{
    		parameters.addValue(parameterName, userModel.getUserStateId());
    	}*/
    	else if(insertColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, calendarHistoryModel.getCreatedAt());
    	}
    	/*else if(insertColumnName.equals(User.getColumnName(User.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, userModel.getUpdatedAt());
    	}*/
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CalendarHistory calendarHistoryModel)
	{
    	if(keyHolderColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.ID)))
    	{
    		calendarHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	if(keyHolderColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.CALENDAR_ID)))
    	{
    		calendarHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.YEAR)))
    	{
    		calendarHistoryModel.setYear((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.SEMESTER)))
    	{
    		calendarHistoryModel.setSemester((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.DAYS)))
    	{
    		calendarHistoryModel.setDays((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.START_TIME)))
    	{
    		calendarHistoryModel.setStartTime((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.END_TIME)))
    	{
    		calendarHistoryModel.setEndTime((String) keyMap.get(keyHolderColumnName));
    	}
    	/*else if(keyHolderColumnName.equals(User.getColumnName(User.Columns.SALT)))
    	{
    		userModel.setSalt((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User.getColumnName(User.Columns.USER_STATE_ID)))
    	{
    		userModel.setUserStateId((Integer) keyMap.get(keyHolderColumnName));
    	}*/
    	else if(keyHolderColumnName.equals(CalendarHistory.getColumnName(CalendarHistory.Columns.CREATED_AT)))
    	{
    		calendarHistoryModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	/*else if(keyHolderColumnName.equals(User.getColumnName(User.Columns.UPDATED_AT)))
    	{
    		userModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}*/
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
    	}
	}
	
	@Override
	public void validateColumnNames(List<String> columnNameList)
	{
		List<String> actualColumnNames = CalendarHistory.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}


	@Override
	public CalendarHistory findByCalendarId(int calendar_id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(CalendarHistory.getColumnName(CalendarHistory.Columns.CALENDAR_ID), false);
		List<String> selectColumnNames = CalendarHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, calendar_id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<CalendarHistory> calendarHistoryList = select(selectColumnNames, queryTermList, orderByList);
	
	    CalendarHistory calendarHistory = null;
	    
	    if(!calendarHistoryList.isEmpty())
	    {
	    	calendarHistory = calendarHistoryList.get(0);
	    }
	    
	    return calendarHistory;
	}
}