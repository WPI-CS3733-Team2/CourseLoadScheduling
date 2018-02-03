package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CourseLoadAssociationDao;
import org.dselent.scheduling.server.extractor.CourseLoadAssociationExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.CourseLoadAssociation;
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
public class CourseLoadAssociationDaoImpl extends BaseDaoImpl<CourseLoadAssociation> implements CourseLoadAssociationDao
{
	@Override
	public int insert(CourseLoadAssociation courseLoadAssociationModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList); 				//check if the provided columns are included in the columns
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(CourseLoadAssociation.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, courseLoadAssociationModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, courseLoadAssociationModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<CourseLoadAssociation> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		CourseLoadAssociationExtractor extractor = new CourseLoadAssociationExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(CourseLoadAssociation.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<CourseLoadAssociation> courseLoadAssociationList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return courseLoadAssociationList;
	}

	@Override
	public CourseLoadAssociation findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.ID), false);
		List<String> selectColumnNames = CourseLoadAssociation.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<CourseLoadAssociation> courseLoadAssociationList = select(selectColumnNames, queryTermList, orderByList);
	
	    CourseLoadAssociation courseLoadAssociation = null;
	    
	    if(!courseLoadAssociationList.isEmpty())
	    {
	    	courseLoadAssociation = courseLoadAssociationList.get(0);
	    }
	    
	    return courseLoadAssociation;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(CourseLoadAssociation.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(CourseLoadAssociation.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseLoadAssociation courseLoadAssociationModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.ID)))
    	{
    		parameters.addValue(parameterName, courseLoadAssociationModel.getId());
    	}
    	else if(insertColumnName.equals(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID)))			//
    	{
    		parameters.addValue(parameterName, courseLoadAssociationModel.getFacultyId());
    	}
    	else if(insertColumnName.equals(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.COURSE_LOAD_ID)))
    	{
    		parameters.addValue(parameterName, courseLoadAssociationModel.getCourseLoadId());
    	}
    	else if(insertColumnName.equals(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.DELETED))) {			//
    		parameters.addValue(parameterName, courseLoadAssociationModel.getDeleted());
    	}
    	else if(insertColumnName.equals(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, courseLoadAssociationModel.getCreatedAt());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseLoadAssociation courseLoadAssociationModel)
	{
    	if(keyHolderColumnName.equals(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.ID)))
    	{
    		courseLoadAssociationModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID))) {		//
    		courseLoadAssociationModel.setFacultyId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.COURSE_LOAD_ID)))
    	{
    		courseLoadAssociationModel.setCourseLoadId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.DELETED))) {			//
    		courseLoadAssociationModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.CREATED_AT)))
    	{
    		courseLoadAssociationModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = CourseLoadAssociation.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}


	@Override
	public CourseLoadAssociation findByFacultyId(int faculty_id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID), false);
		List<String> selectColumnNames = CourseLoadAssociation.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, faculty_id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<CourseLoadAssociation> courseLoadAssociationList = select(selectColumnNames, queryTermList, orderByList);
	
	    CourseLoadAssociation courseLoadAssociation = null;
	    
	    if(!courseLoadAssociationList.isEmpty())
	    {
	    	courseLoadAssociation = courseLoadAssociationList.get(0);
	    }
	    
	    return courseLoadAssociation;
	}


	@Override
	public CourseLoadAssociation findByCourseLoadId(int courseLoad_id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.COURSE_LOAD_ID), false);
		List<String> selectColumnNames = CourseLoadAssociation.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, courseLoad_id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<CourseLoadAssociation> courseLoadAssociationList = select(selectColumnNames, queryTermList, orderByList);
	
	    CourseLoadAssociation courseLoadAssociation = null;
	    
	    if(!courseLoadAssociationList.isEmpty())
	    {
	    	courseLoadAssociation = courseLoadAssociationList.get(0);
	    }
	    
	    return courseLoadAssociation;
	}
}
