package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.UsersHistoryDao;
import org.dselent.scheduling.server.extractor.UserRoleExtractor;
import org.dselent.scheduling.server.extractor.UsersHistoryExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.UserRole;
import org.dselent.scheduling.server.model.UsersHistory;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UsersHistoryDaoImpl extends BaseDaoImpl<UsersHistory> implements UsersHistoryDao {

	@Override
	public int insert(UsersHistory model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList)
			throws SQLException {
		
		validateColumnNames(insertColumnNameList); 				//check if the provided columns are included in the columns
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(UsersHistory.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, model);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, model);
	    }
	    	    
	    return rowsAffected;
		
	}

	@Override
	public List<UsersHistory> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList,
			List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		UsersHistoryExtractor extractor = new UsersHistoryExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(UsersHistory.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<UsersHistory> usersHistoryList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return usersHistoryList;
	}

	@Override
	public UsersHistory findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(UsersHistory.getColumnName(UsersHistory.Columns.ID), false);
		List<String> selectColumnNames = UsersHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<UsersHistory> usersHistoryList = select(selectColumnNames, queryTermList, orderByList);
	
	    UsersHistory usersHistory = null;
	    
	    if(!usersHistoryList.isEmpty())
	    {
	    	usersHistory = usersHistoryList.get(0);
	    }
	    
	    return usersHistory;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		String queryTemplate = QueryStringBuilder.generateUpdateString(UsersHistory.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(UsersHistory.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, UsersHistory model) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		// Wish this could generalize
		// The getter must be distinguished unless the models are designed as simply a
		// map of columns-values
		// Would prefer not being that generic since it may end up leading to all code
		// being collections of strings

		if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.ID))) {
			parameters.addValue(parameterName, model.getId());
		} 
		else if(insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.USER_ID))) {
			parameters.addValue(parameterName, model.getUserId());
		}
		else if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.WPI_ID))){
			parameters.addValue(parameterName, model.getWpiId());
		} 
		else if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.USER_NAME))) {
			parameters.addValue(parameterName, model.getUserName());
		} 
		else if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.FIRST_NAME))) {
			parameters.addValue(parameterName, model.getFirstName());
		} 
		else if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.LAST_NAME))) {
			parameters.addValue(parameterName, model.getLastName());
		} 
		else if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.EMAIL))) {
			parameters.addValue(parameterName, model.getEmail());
		} 
		else if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.ENCRYPTED_PASSWORD))) {
			parameters.addValue(parameterName, model.getEncryptedPassword());
		} 
		else if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.SALT))) {
			parameters.addValue(parameterName, model.getSalt());
		} 
		else if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.ACCOUNT_STATE))) //
		{
			parameters.addValue(parameterName, model.getAccount_state());
		} 
		else if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.DELETED))) { //
			parameters.addValue(parameterName, model.getDeleted());
		} 
		else if (insertColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.CREATED_AT))) {
			parameters.addValue(parameterName, model.getCreatedAt());
		}
		else {
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, UsersHistory model) {
		if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.ID))) {
			model.setId((Integer) keyMap.get(keyHolderColumnName));
		} 
		else if(keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.USER_ID))) {
			model.setUserId((Integer) keyMap.get(keyHolderColumnName));
		}
		else if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.WPI_ID))) { //
			model.setWpiId((String) keyMap.get(keyHolderColumnName));
		} 
		else if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.USER_NAME))) {
			model.setUserName((String) keyMap.get(keyHolderColumnName));
		} 
		else if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.FIRST_NAME))) {
			model.setFirstName((String) keyMap.get(keyHolderColumnName));
		} 
		else if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.LAST_NAME))) {
			model.setLastName((String) keyMap.get(keyHolderColumnName));
		} 
		else if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.EMAIL))) {
			model.setEmail((String) keyMap.get(keyHolderColumnName));
		} 
		else if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.ENCRYPTED_PASSWORD))) {
			model.setEncryptedPassword((String) keyMap.get(keyHolderColumnName));
		} 
		else if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.SALT))) {
			model.setSalt((String) keyMap.get(keyHolderColumnName));
		} 
		else if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.ACCOUNT_STATE))) {
			model.setAccount_state((String) keyMap.get(keyHolderColumnName));
		} 
		else if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.DELETED))) { //
			model.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
		} 
		else if (keyHolderColumnName.equals(UsersHistory.getColumnName(UsersHistory.Columns.CREATED_AT))) {
			model.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} 
		else {
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}
	
	
	@Override
	public void validateColumnNames(List<String> columnNameList) {
		List<String> actualColumnNames = UsersHistory.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}

	@Override
	public UsersHistory findByUserId(int uid) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(UsersHistory.getColumnName(UsersHistory.Columns.USER_ID), false);
		List<String> selectColumnNames = UsersHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, uid, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<UsersHistory> usersHistoryList = select(selectColumnNames, queryTermList, orderByList);
	
	    UsersHistory usersHistory = null;
	    
	    if(!usersHistoryList.isEmpty())
	    {
	    	usersHistory = usersHistoryList.get(0);
	    }
	    
	    return usersHistory;
	}

}
