package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.UserFacultyAssociationDao;
import org.dselent.scheduling.server.extractor.UserFacultyAssociationExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.UserFacultyAssociation;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserFacultyAssociationDaoImpl extends BaseDaoImpl<UserFacultyAssociation>
		implements UserFacultyAssociationDao {

	@Override
	public int insert(UserFacultyAssociation userFacultyAssociationModel, List<String> insertColumnNameList,
			List<String> keyHolderColumnNameList) throws SQLException {

		validateColumnNames(insertColumnNameList); // check if the provided columns are included in the columns
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(UserFacultyAssociation.TABLE_NAME,
				insertColumnNameList);
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		List<Map<String, Object>> keyList = new ArrayList<>();
		KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

		for (String insertColumnName : insertColumnNameList) {
			addParameterMapValue(parameters, insertColumnName, userFacultyAssociationModel);
		}
		// new way, but unfortunately unnecessary class creation is slow and wasteful
		// (i.e. wrong)
		// insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters,
		// insertColumnName, userModel));

		int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

		Map<String, Object> keyMap = keyHolder.getKeys();

		for (String keyHolderColumnName : keyHolderColumnNameList) {
			addObjectValue(keyMap, keyHolderColumnName, userFacultyAssociationModel);
		}

		return rowsAffected;

	}

	@Override
	public List<UserFacultyAssociation> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList,
			List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		// TODO Auto-generated method stub
		UserFacultyAssociationExtractor extractor = new UserFacultyAssociationExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(UserFacultyAssociation.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<UserFacultyAssociation> userFacultyAssociationsList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return userFacultyAssociationsList;
	}

	@Override
	public UserFacultyAssociation findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		String columnName = QueryStringBuilder.convertColumnName(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.ID), false);
		List<String> selectColumnNames = UserFacultyAssociation.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<UserFacultyAssociation> userFacultyAssociationsList = select(selectColumnNames, queryTermList, orderByList);
	
	    UserFacultyAssociation userFacultyAssociation = null;
	    
	    if(!userFacultyAssociationsList.isEmpty())
	    {
	    	userFacultyAssociation = userFacultyAssociationsList.get(0);
	    }
	    
	    return userFacultyAssociation;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		// TODO Auto-generated method stub
		String queryTemplate = QueryStringBuilder.generateUpdateString(UserFacultyAssociation.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(UserFacultyAssociation.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, UserFacultyAssociation userFacultyAssociationModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		// Wish this could generalize
		// The getter must be distinguished unless the models are designed as simply a
		// map of columns-values
		// Would prefer not being that generic since it may end up leading to all code
		// being collections of strings

		if (insertColumnName.equals(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.ID))) {
			parameters.addValue(parameterName, userFacultyAssociationModel.getId());
		} 
		else if (insertColumnName.equals(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.USER_ID))){
			parameters.addValue(parameterName, userFacultyAssociationModel.getUserId());
		}
		else if (insertColumnName.equals(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.FACULTY_ID))){
			parameters.addValue(parameterName, userFacultyAssociationModel.getFacultyId());
		}
		else if (insertColumnName.equals(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.CREATED_AT))) {
			parameters.addValue(parameterName, userFacultyAssociationModel.getCreatedAt());
		}
		else {
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName,
			UserFacultyAssociation userFacultyAssociationModel) {
		if (keyHolderColumnName.equals(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.ID))) {
			userFacultyAssociationModel.setId((Integer) keyMap.get(keyHolderColumnName));
		} else if (keyHolderColumnName
				.equals(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.USER_ID))) { //
			userFacultyAssociationModel.setUserId((Integer) keyMap.get(keyHolderColumnName));
		} else if (keyHolderColumnName
				.equals(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.FACULTY_ID))) {
			userFacultyAssociationModel.setFacultyId((Integer) keyMap.get(keyHolderColumnName));
		} else if (keyHolderColumnName
				.equals(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.CREATED_AT))) {
			userFacultyAssociationModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} else {
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}

	@Override
	public void validateColumnNames(List<String> columnNameList) {
		// TODO Auto-generated method stub
		List<String> actualColumnNames = UserFacultyAssociation.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);

		if (!valid) {
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);

			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}

	@Override
	public UserFacultyAssociation findByFacultyId(int fid) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.FACULTY_ID), false);
		List<String> selectColumnNames = UserFacultyAssociation.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, fid, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<UserFacultyAssociation> userFacultyAssociationsList = select(selectColumnNames, queryTermList, orderByList);
	
	    UserFacultyAssociation userFacultyAssociation = null;
	    
	    if(!userFacultyAssociationsList.isEmpty())
	    {
	    	userFacultyAssociation = userFacultyAssociationsList.get(0);
	    }
	    
	    return userFacultyAssociation;
	}

	@Override
	public UserFacultyAssociation findByUserId(int uid) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.USER_ID), false);
		List<String> selectColumnNames = UserFacultyAssociation.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, uid, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<UserFacultyAssociation> userFacultyAssociationsList = select(selectColumnNames, queryTermList, orderByList);
	
	    UserFacultyAssociation userFacultyAssociation = null;
	    
	    if(!userFacultyAssociationsList.isEmpty())
	    {
	    	userFacultyAssociation = userFacultyAssociationsList.get(0);
	    }
	    
	    return userFacultyAssociation;
	}

}
