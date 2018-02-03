package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.extractor.SectionsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Section;
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
public class SectionsDaoImpl extends BaseDaoImpl<Section> implements SectionsDao
{
	@Override
	public int insert(Section sectionModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList); 				//check if the provided columns are included in the columns
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Section.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, sectionModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, sectionModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, sectionModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<Section> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Section.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<Section> sectionsList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return sectionsList;
	}

	@Override
	public Section findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(Section.getColumnName(Section.Columns.ID), false);
		List<String> selectColumnNames = Section.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Section> sectionsList = select(selectColumnNames, queryTermList, orderByList);
	
	    Section section = null;
	    
	    if(!sectionsList.isEmpty())
	    {
	    	section = sectionsList.get(0);
	    }
	    
	    return section;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(Section.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(Section.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Section SectionModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(Section.getColumnName(Section.Columns.ID)))
    	{
    		parameters.addValue(parameterName, SectionModel.getId());
    	}
    	else if(insertColumnName.equals(Section.getColumnName(Section.Columns.CRN)))
    	{
    		parameters.addValue(parameterName, SectionModel.getCrn());
    	}
    	else if(insertColumnName.equals(Section.getColumnName(Section.Columns.NAME)))
    	{
    		parameters.addValue(parameterName, SectionModel.getName());
    	}
    	else if(insertColumnName.equals(Section.getColumnName(Section.Columns.TYPE)))
    	{
    		parameters.addValue(parameterName, SectionModel.getType());
    	}
    	else if(insertColumnName.equals(Section.getColumnName(Section.Columns.EXPECTED_POPULATION)))
    	{
    		parameters.addValue(parameterName, SectionModel.getExpectedPopulation());
    	}
    	else if(insertColumnName.equals(Section.getColumnName(Section.Columns.COURSE_ID)))
    	{
    		parameters.addValue(parameterName, SectionModel.getCourseId());
    	}
    	else if(insertColumnName.equals(Section.getColumnName(Section.Columns.CALENDAR_ID)))
    	{
    		parameters.addValue(parameterName, SectionModel.getCalendarId());
    	}
    	else if(insertColumnName.equals(Section.getColumnName(Section.Columns.SCHEDULE_ID)))
    	{
    		parameters.addValue(parameterName, SectionModel.getScheduleId());
    	}
    	else if(insertColumnName.equals(Section.getColumnName(Section.Columns.CREATED_AT)))		//
    	{
    		parameters.addValue(parameterName, SectionModel.getCreatedAt());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Section sectionModel)
	{
    	if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.ID)))
    	{
    		sectionModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.CRN))) {		//
    		sectionModel.setCrn((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.NAME)))
    	{
    		sectionModel.setName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.TYPE)))
    	{
    		sectionModel.setType((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.EXPECTED_POPULATION)))
    	{
    		sectionModel.setExpectedPopulation((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.COURSE_ID)))
    	{
    		sectionModel.setCourseId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.CALENDAR_ID)))
    	{
    		sectionModel.setCalendarId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.SCHEDULE_ID)))
    	{
    		sectionModel.setScheduleId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.CREATED_AT)))
    	{
    		sectionModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = Section.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}


	@Override
	public Section findByCrn(int crn) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Section.getColumnName(Section.Columns.CRN), false);
		List<String> selectColumnNames = Section.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, crn, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Section> sectionsList = select(selectColumnNames, queryTermList, orderByList);
	
	    Section section = null;
	    
	    if(!sectionsList.isEmpty())
	    {
	    	section = sectionsList.get(0);
	    }
	    
	    return section;
	}


	@Override
	public Section findByName(String name) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Section.getColumnName(Section.Columns.NAME), false);
		List<String> selectColumnNames = Section.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, name, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Section> sectionsList = select(selectColumnNames, queryTermList, orderByList);
	
	    Section section = null;
	    
	    if(!sectionsList.isEmpty())
	    {
	    	section = sectionsList.get(0);
	    }
	    
	    return section;
	}


	@Override
	public Section findByType(String type) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Section.getColumnName(Section.Columns.TYPE), false);
		List<String> selectColumnNames = Section.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, type, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Section> sectionsList = select(selectColumnNames, queryTermList, orderByList);
	
	    Section section = null;
	    
	    if(!sectionsList.isEmpty())
	    {
	    	section = sectionsList.get(0);
	    }
	    
	    return section;
	}


	@Override
	public Section findByPopulation(int population) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Section.getColumnName(Section.Columns.EXPECTED_POPULATION), false);
		List<String> selectColumnNames = Section.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, population, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Section> sectionsList = select(selectColumnNames, queryTermList, orderByList);
	
	    Section section = null;
	    
	    if(!sectionsList.isEmpty())
	    {
	    	section = sectionsList.get(0);
	    }
	    
	    return section;
	}


	@Override
	public Section findByCourseId(int courseId) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Section.getColumnName(Section.Columns.COURSE_ID), false);
		List<String> selectColumnNames = Section.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, courseId, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Section> sectionsList = select(selectColumnNames, queryTermList, orderByList);
	
	    Section section = null;
	    
	    if(!sectionsList.isEmpty())
	    {
	    	section = sectionsList.get(0);
	    }
	    
	    return section;
	}


	@Override
	public Section findByScheduleId(int scheduleId) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Section.getColumnName(Section.Columns.SCHEDULE_ID), false);
		List<String> selectColumnNames = Section.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, scheduleId, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Section> sectionsList = select(selectColumnNames, queryTermList, orderByList);
	
	    Section section = null;
	    
	    if(!sectionsList.isEmpty())
	    {
	    	section = sectionsList.get(0);
	    }
	    
	    return section;
	}
}
