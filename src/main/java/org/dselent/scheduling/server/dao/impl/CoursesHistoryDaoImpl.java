package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CoursesHistoryDao;
import org.dselent.scheduling.server.extractor.CoursesHistoryExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.CourseHistory;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
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
public class CoursesHistoryDaoImpl extends BaseDaoImpl<CourseHistory> implements CoursesHistoryDao
{
	@Override
	public int insert(CourseHistory coursesHistoryModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(CourseHistory.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, coursesHistoryModel);
	    }
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, coursesHistoryModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<CourseHistory> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		CoursesHistoryExtractor extractor = new CoursesHistoryExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(CourseHistory.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<CourseHistory> coursesHistoryList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return coursesHistoryList;
	}

	@Override
	public CourseHistory findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(CourseHistory.getColumnName(CourseHistory.Columns.ID), false);
		List<String> selectColumnNames = Course.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<CourseHistory> coursesHistoryList = select(selectColumnNames, queryTermList, orderByList);
	
		CourseHistory coursesHistory = null;
	    
	    if(!coursesHistoryList.isEmpty())
	    {
	    coursesHistory = coursesHistoryList.get(0);
	    }
	    
	    return coursesHistory;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(CourseHistory.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(CourseHistory.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseHistory coursesHistoryModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
		
    	if(insertColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.ID)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getId());
    	}
    	else if(insertColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.COURSE_ID)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getId());
    	}
    	else if(insertColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.NUMBER)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getNumber());
    	}
    	else if(insertColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.NAME)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getName());
    	}
    	else if(insertColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.FREQUENCY)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getFrequency());
    	}
    	else if(insertColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getCreatedAt());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseHistory coursesHistoryModel)
	{
    	if(keyHolderColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.ID)))
    	{
    		coursesHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.COURSE_ID)))
    	{
    		coursesHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.NUMBER)))
    	{
    		coursesHistoryModel.setNumber((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.NAME)))
    	{
    		coursesHistoryModel.setName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.FREQUENCY)))
    	{
    		coursesHistoryModel.setFrequency((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseHistory.getColumnName(CourseHistory.Columns.CREATED_AT)))
    	{
    		coursesHistoryModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
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
		List<String> actualColumnNames = CourseHistory.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
