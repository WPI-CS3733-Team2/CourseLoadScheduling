package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.RequestDao;
import org.dselent.scheduling.server.extractor.RequestExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Request;
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
public class RequestDaoImpl extends BaseDaoImpl<Request> implements RequestDao
{
	
	@Override
	public int insert(Request requestModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList); 				//check if the provided columns are included in the columns
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Request.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, requestModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, requestModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, requestModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<Request> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		RequestExtractor extractor = new RequestExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Request.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<Request> requestList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return requestList;
	}

	@Override
	public Request findById(int id) throws SQLException
	{
		String columnName = Request.getColumnName(Request.Columns.ID);
		List<String> selectColumnNames = Request.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Request> requestList = select(selectColumnNames, queryTermList, orderByList);
	
		Request request = null;
	    
	    if(!requestList.isEmpty())
	    {
	    	request = requestList.get(0);
	    }
	    
	    return request;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(Request.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(Request.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Request requestModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(Request.getColumnName(Request.Columns.ID)))
    	{
    		parameters.addValue(parameterName, requestModel.getId());
    	}
    	else if(insertColumnName.equals(Request.getColumnName(Request.Columns.FACULTY_ID)))
    	{
    		parameters.addValue(parameterName, requestModel.getFacultyId());
    	}
    	else if(insertColumnName.equals(Request.getColumnName(Request.Columns.TYPE)))
    	{
    		parameters.addValue(parameterName, requestModel.getType());
    	}
    	else if(insertColumnName.equals(Request.getColumnName(Request.Columns.STATE)))
    	{
    		parameters.addValue(parameterName, requestModel.getState());
    	}
    	else if(insertColumnName.equals(Request.getColumnName(Request.Columns.COURSE)))
    	{
    		parameters.addValue(parameterName, requestModel.getCourse());
    	}
    	else if(insertColumnName.equals(Request.getColumnName(Request.Columns.SECTION)))
    	{
    		parameters.addValue(parameterName, requestModel.getSection());
    	}
    	else if(insertColumnName.equals(Request.getColumnName(Request.Columns.DATA)))
    	{
    		parameters.addValue(parameterName, requestModel.getData());
    	}
    	else if(insertColumnName.equals(Request.getColumnName(Request.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, requestModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(Request.getColumnName(Request.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, requestModel.getUpdatedAt());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Request requestModel)
	{
    	if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.ID))){
    		requestModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.FACULTY_ID))) {		//
    		requestModel.setFacultyId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.TYPE)))
    	{
    		requestModel.setType((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.STATE)))
    	{
    		requestModel.setState((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.COURSE)))
    	{
    		requestModel.setCourse((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.SECTION)))
    	{
    		requestModel.setSection((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.DATA)))
    	{
    		requestModel.setData((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.CREATED_AT)))
    	{
    		requestModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.UPDATED_AT)))
    	{
    		requestModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = Request.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}


	@Override
	public Request findByFacultyId(int facultyId) throws SQLException {
		String columnName = Request.getColumnName(Request.Columns.FACULTY_ID);
		List<String> selectColumnNames = Request.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, facultyId, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Request> requestList = select(selectColumnNames, queryTermList, orderByList);
	
		Request request = null;
	    
	    if(!requestList.isEmpty())
	    {
	    	request = requestList.get(0);
	    }
	    
	    return request;
	}


	@Override
	public Request findByType(int type) throws SQLException {
		String columnName = Request.getColumnName(Request.Columns.TYPE);
		List<String> selectColumnNames = Request.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, type, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Request> requestList = select(selectColumnNames, queryTermList, orderByList);
	
		Request request = null;
	    
	    if(!requestList.isEmpty())
	    {
	    	request = requestList.get(0);
	    }
	    
	    return request;
	}


	@Override
	public Request findByState(int state) throws SQLException {
		String columnName = Request.getColumnName(Request.Columns.STATE);
		List<String> selectColumnNames = Request.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, state, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Request> requestList = select(selectColumnNames, queryTermList, orderByList);
	
		Request request = null;
	    
	    if(!requestList.isEmpty())
	    {
	    	request = requestList.get(0);
	    }
	    
	    return request;
	}


	@Override
	public Request findByCourseId(int courseId) throws SQLException {
		String columnName = Request.getColumnName(Request.Columns.COURSE);
		List<String> selectColumnNames = Request.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, courseId, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Request> requestList = select(selectColumnNames, queryTermList, orderByList);
	
		Request request = null;
	    
	    if(!requestList.isEmpty())
	    {
	    	request = requestList.get(0);
	    }
	    
	    return request;
	}


	@Override
	public Request findBySectionId(int sectionId) throws SQLException {
		String columnName = Request.getColumnName(Request.Columns.SECTION);
		List<String> selectColumnNames = Request.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, sectionId, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Request> requestList = select(selectColumnNames, queryTermList, orderByList);
	
		Request request = null;
	    
	    if(!requestList.isEmpty())
	    {
	    	request = requestList.get(0);
	    }
	    
	    return request;
	}
}
