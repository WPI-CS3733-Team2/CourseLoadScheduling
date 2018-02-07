package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.FacultyDao;
import org.dselent.scheduling.server.extractor.FacultyExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class FacultyDaoImpl extends BaseDaoImpl<Faculty> implements FacultyDao {

	@Override
	public int insert(Faculty facultyModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList)
			throws SQLException {
		validateColumnNames(insertColumnNameList); 				//check if the provided columns are included in the columns
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Faculty.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList){
	    		addParameterMapValue(parameters, insertColumnName, facultyModel);
	    }

	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList){
	    		addObjectValue(keyMap, keyHolderColumnName, facultyModel);
	    }
	    
	    //List<Map<String,Object>> returnMap= keyHolder.getKeyList();
	   
	    	return (int)keyMap.get("id");
	    //eturn id;
	}

	@Override
	public List<Faculty> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList,
			List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		// TODO Auto-generated method stub
		FacultyExtractor extractor = new FacultyExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Faculty.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<Faculty> facultiesList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return facultiesList;
	}

	@Override
	public Faculty findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		String columnName = QueryStringBuilder.convertColumnName(Faculty.getColumnName(Faculty.Columns.ID), false);
		List<String> selectColumnNames = Faculty.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Faculty> facultiesList = select(selectColumnNames, queryTermList, orderByList);
	
	    Faculty faculty = null;
	    
	    if(!facultiesList.isEmpty())
	    {
	    	faculty = facultiesList.get(0);
	    }
	    
	    return faculty;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		// TODO Auto-generated method stub
		String queryTemplate = QueryStringBuilder.generateUpdateString(Faculty.TABLE_NAME, columnName, queryTermList);

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
	public int delete(List<QueryTerm> queryTermList) throws SQLException {
		// TODO Auto-generated method stub
		String queryTemplate = QueryStringBuilder.generateDeleteString(Faculty.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Faculty facultyModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.ID)))
    	{
    		parameters.addValue(parameterName, facultyModel.getId());
    	}
    	else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.RANK)))			//
    	{
    		parameters.addValue(parameterName, facultyModel.getRank());
    	}
    	else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.ASSIGNED)))
    	{
    		parameters.addValue(parameterName, facultyModel.getAssigned());
    	}
    	else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, facultyModel.getDeleted());
    	}
    	else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, facultyModel.getCreatedAt());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Faculty facultyModel)
	{
    	if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.ID)))
    	{
    		facultyModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.RANK))) {		//
    		facultyModel.setRank((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.ASSIGNED)))
    	{
    		facultyModel.setAssigned((Boolean) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.DELETED)))
    	{
    		facultyModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.CREATED_AT)))
    	{
    		facultyModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
    	}
	}
		
	
	@Override
	public void validateColumnNames(List<String> columnNameList) {
		// TODO Auto-generated method stub
		List<String> actualColumnNames = Faculty.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}

	}

	@Override
	public Faculty findByRank(int rank) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Faculty.getColumnName(Faculty.Columns.RANK), false);
		List<String> selectColumnNames = Faculty.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, rank, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Faculty> facultiesList = select(selectColumnNames, queryTermList, orderByList);
	
	    Faculty faculty = null;
	    
	    if(!facultiesList.isEmpty())
	    {
	    	faculty = facultiesList.get(0);
	    }
	    
	    return faculty;
	}

	@Override
	public Faculty findByIfAssigned(Boolean ifAssigned) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Faculty.getColumnName(Faculty.Columns.ASSIGNED), false);
		List<String> selectColumnNames = Faculty.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, ifAssigned, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Faculty> facultiesList = select(selectColumnNames, queryTermList, orderByList);
	
	    Faculty faculty = null;
	    
	    if(!facultiesList.isEmpty())
	    {
	    	faculty = facultiesList.get(0);
	    }
	    
	    return faculty;
	}

	@Override
	public Faculty findByIfDeleted(Boolean ifDeleted) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Faculty.getColumnName(Faculty.Columns.DELETED), false);
		List<String> selectColumnNames = Faculty.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, ifDeleted, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Faculty> facultiesList = select(selectColumnNames, queryTermList, orderByList);
	
	    Faculty faculty = null;
	    
	    if(!facultiesList.isEmpty())
	    {
	    	faculty = facultiesList.get(0);
	    }
	    
	    return faculty;
	}

}
