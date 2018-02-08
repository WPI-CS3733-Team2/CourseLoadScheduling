package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CalendarDao;
import org.dselent.scheduling.server.extractor.CalendarExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Calendar;
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
public class CalendarDaoImpl extends BaseDaoImpl<Calendar> implements CalendarDao
{
	@Override
	public int insert(Calendar calendarModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Calendar.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, calendarModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, calendarModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<Calendar> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		CalendarExtractor extractor = new CalendarExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Calendar.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<Calendar> calendarList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return calendarList;
	}

	@Override
	public Calendar findById(int id) throws SQLException
	{
		String columnName = Calendar.getColumnName(Calendar.Columns.ID);
		List<String> selectColumnNames = Calendar.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Calendar> calendarList = select(selectColumnNames, queryTermList, orderByList);
	
	    Calendar calendar = null;
	    
	    if(!calendarList.isEmpty())
	    {
	    	calendar = calendarList.get(0);
	    }
	    
	    return calendar;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		
		String queryTemplate = QueryStringBuilder.generateUpdateString(Calendar.TABLE_NAME, columnName, queryTermList);
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
		String queryTemplate = QueryStringBuilder.generateDeleteString(Calendar.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Calendar calendarModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(Calendar.getColumnName(Calendar.Columns.ID)))
    	{
    		parameters.addValue(parameterName, calendarModel.getId());
    	}
    	else if(insertColumnName.equals(Calendar.getColumnName(Calendar.Columns.YEAR)))
    	{
    		parameters.addValue(parameterName, calendarModel.getYear());
    	}
    	else if(insertColumnName.equals(Calendar.getColumnName(Calendar.Columns.SEMESTER)))
    	{
    		parameters.addValue(parameterName, calendarModel.getSemester());
    	}
    	else if(insertColumnName.equals(Calendar.getColumnName(Calendar.Columns.DAYS)))
    	{
    		parameters.addValue(parameterName, calendarModel.getDays());
    	}
    	else if(insertColumnName.equals(Calendar.getColumnName(Calendar.Columns.START_TIME)))
    	{
    		parameters.addValue(parameterName, calendarModel.getStartTime());
    	}
    	else if(insertColumnName.equals(Calendar.getColumnName(Calendar.Columns.END_TIME)))
    	{
    		parameters.addValue(parameterName, calendarModel.getEndTime());
    	}
    	/*else if(insertColumnName.equals(User.getColumnName(User.Columns.SALT)))
    	{
    		parameters.addValue(parameterName, userModel.getSalt());
    	}
    	else if(insertColumnName.equals(User.getColumnName(User.Columns.USER_STATE_ID)))
    	{
    		parameters.addValue(parameterName, userModel.getUserStateId());
    	}*/
    	else if(insertColumnName.equals(Calendar.getColumnName(Calendar.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, calendarModel.getCreatedAt());
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

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Calendar calendarModel)
	{
    	if(keyHolderColumnName.equals(Calendar.getColumnName(Calendar.Columns.ID)))
    	{
    		calendarModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Calendar.getColumnName(Calendar.Columns.YEAR)))
    	{
    		calendarModel.setYear((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Calendar.getColumnName(Calendar.Columns.SEMESTER)))
    	{
    		calendarModel.setSemester((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Calendar.getColumnName(Calendar.Columns.DAYS)))
    	{
    		calendarModel.setDays((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Calendar.getColumnName(Calendar.Columns.START_TIME)))
    	{
    		calendarModel.setStartTime((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Calendar.getColumnName(Calendar.Columns.END_TIME)))
    	{
    		calendarModel.setEndTime((String) keyMap.get(keyHolderColumnName));
    	}
    	/*else if(keyHolderColumnName.equals(User.getColumnName(User.Columns.SALT)))
    	{
    		userModel.setSalt((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User.getColumnName(User.Columns.USER_STATE_ID)))
    	{
    		userModel.setUserStateId((Integer) keyMap.get(keyHolderColumnName));
    	}*/
    	else if(keyHolderColumnName.equals(Calendar.getColumnName(Calendar.Columns.CREATED_AT)))
    	{
    		calendarModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = Calendar.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}


	@Override
	public Calendar findByYear(int year) throws SQLException {
		//String columnName = QueryStringBuilder.convertColumnName(Calendar.getColumnName(Calendar.Columns.YEAR), false);
		String columnName = Calendar.getColumnName(Calendar.Columns.YEAR);
		List<String> selectColumnNames = Calendar.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, year, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Calendar> calendarList = select(selectColumnNames, queryTermList, orderByList);
	
	    Calendar calendar = null;
	    
	    if(!calendarList.isEmpty())
	    {
	    	calendar = calendarList.get(0);
	    }
	    
	    return calendar;
	}


	@Override
	public Calendar findBySemester(String semester) throws SQLException {
		//String columnName = QueryStringBuilder.convertColumnName(Calendar.getColumnName(Calendar.Columns.SEMESTER), false);
		String columnName = Calendar.getColumnName(Calendar.Columns.SEMESTER);
		List<String> selectColumnNames = Calendar.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, semester, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Calendar> calendarList = select(selectColumnNames, queryTermList, orderByList);
	
	    Calendar calendar = null;
	    
	    if(!calendarList.isEmpty())
	    {
	    	calendar = calendarList.get(0);
	    }
	    
	    return calendar;
	}


	@Override
	public Calendar findByDay(String day) throws SQLException {
		String columnName = Calendar.getColumnName(Calendar.Columns.DAYS);
		List<String> selectColumnNames = Calendar.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, day, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Calendar> calendarList = select(selectColumnNames, queryTermList, orderByList);
	
	    Calendar calendar = null;
	    
	    if(!calendarList.isEmpty())
	    {
	    	calendar = calendarList.get(0);
	    }
	    
	    return calendar;
	}


	@Override
	public Calendar findByStartingTime(String startTime) throws SQLException {
		String columnName = Calendar.getColumnName(Calendar.Columns.START_TIME);
		List<String> selectColumnNames = Calendar.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, startTime, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Calendar> calendarList = select(selectColumnNames, queryTermList, orderByList);
	
	    Calendar calendar = null;
	    
	    if(!calendarList.isEmpty())
	    {
	    	calendar = calendarList.get(0);
	    }
	    
	    return calendar;
	}


	@Override
	public Calendar findByEndingTime(String endTime) throws SQLException {
		String columnName = Calendar.getColumnName(Calendar.Columns.END_TIME);
		List<String> selectColumnNames = Calendar.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, endTime, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Calendar> calendarList = select(selectColumnNames, queryTermList, orderByList);
	
	    Calendar calendar = null;
	    
	    if(!calendarList.isEmpty())
	    {
	    	calendar = calendarList.get(0);
	    }
	    
	    return calendar;
	}
}