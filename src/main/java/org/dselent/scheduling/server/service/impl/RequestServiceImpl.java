package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.RequestDao;
import org.dselent.scheduling.server.dao.RequestStateDao;
import org.dselent.scheduling.server.dto.CreateRequestDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Request;
import org.dselent.scheduling.server.model.RequestState;
//import org.dselent.scheduling.server.model.User;
//import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.service.RequestService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestServiceImpl implements RequestService {
	@Autowired
	private RequestDao requestDao;
	@Autowired
	private RequestStateDao requestStateDao;

	public RequestServiceImpl() {
		//
	}

	@Transactional
	@Override
	public List<Integer> createRequest(CreateRequestDto dto) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();

		Request request = new Request();
		request.setFacultyId(Integer.parseInt(dto.getFaculty_id()));
		request.setType(Integer.parseInt(dto.getRequest_type()));
		// request.setState(Integer.parseInt(dto.getRequest_state()));
		request.setCourse(Integer.parseInt(dto.getCourse_id()));
		request.setSection(Integer.parseInt(dto.getSection_id()));

		request.setData(dto.getData());	
		request.setState(pendingInt()); //Should automatically be "pending"
		//For now, coded as if 1=accepted,2=denied,and 3=pending
		//May need another custom DAO to retrieve whatever row in the table is "pending"

		List<String> requestInsertColumnNameList = new ArrayList<>();
		List<String> requestKeyHolderColumnNameList = new ArrayList<>();

		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.FACULTY_ID));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.TYPE));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.STATE));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.COURSE));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.SECTION));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.DATA));

		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.ID));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.CREATED_AT));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.UPDATED_AT));

		rowsAffectedList.add(requestDao.insert(request, requestInsertColumnNameList, requestKeyHolderColumnNameList));

		return rowsAffectedList;
	}

	public List<Request> viewRequestHistory(Integer facultyId) throws SQLException {
		
		String selectColumnName = Request.getColumnName(Request.Columns.FACULTY_ID);
		Integer selectData = facultyId;

		List<QueryTerm> selectQueryTermList = new ArrayList<>();

		QueryTerm selectDataTerm = new QueryTerm();
		selectDataTerm.setColumnName(selectColumnName);
		selectDataTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		selectDataTerm.setValue(selectData);
		selectQueryTermList.add(selectDataTerm);

		List<String> selectColumnNameList = Request.getColumnNameList();

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
		orderByList.add(orderPair1);

		// @SuppressWarnings("unused")
		List<Request> selectedRequestList = requestDao.select(selectColumnNameList, selectQueryTermList, orderByList);

		return selectedRequestList;
	}

	public List<Integer> changeRequestState(Integer requestId, Integer requestState) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();

		String indexColumnName = Request.getColumnName(Request.Columns.ID);
		String updateColumnName = Request.getColumnName(Request.Columns.STATE);

    	Integer newState = requestState;
    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
    	// WHERE id = :requestId
    	QueryTerm updateDataTerm = new QueryTerm();
    	updateDataTerm.setColumnName(indexColumnName);
    	updateDataTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateDataTerm.setValue(requestId);
    	updateQueryTermList.add(updateDataTerm);
    	
    	rowsAffectedList.add(requestDao.update(updateColumnName, newState, updateQueryTermList));
    	
    	return rowsAffectedList;
    }

	public List<Request> viewPendingRequests() throws SQLException{
    	//First retrieves which row in the request state table corresponds to "pending," as this is likely
    	// to vary among group members
    	Integer pending = pendingInt();
    	//Integer pending = 3;
    	String selectColumnName = Request.getColumnName(Request.Columns.STATE);
    	Integer selectData = pending;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectDataTerm = new QueryTerm();
    	selectDataTerm.setColumnName(selectColumnName);
    	selectDataTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectDataTerm.setValue(selectData);
    	selectQueryTermList.add(selectDataTerm);
    	
    	List<String> selectColumnNameList = Request.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		//@SuppressWarnings("unused")
		List<Request> selectedRequestList = requestDao.select(selectColumnNameList, selectQueryTermList, orderByList);

		// For testing
		// System.out.println(selectedRequestList);
		return selectedRequestList;


	}

	private Integer pendingInt() throws SQLException{
    	String selectColumnName0 = RequestState.getColumnName(RequestState.Columns.STATE);
    	String selectData0 = "pending";
    	
    	List<QueryTerm> selectQueryTermList0 = new ArrayList<>();
    	
    	QueryTerm selectDataTerm0 = new QueryTerm();
    	selectDataTerm0.setColumnName(selectColumnName0);
    	selectDataTerm0.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectDataTerm0.setValue(selectData0);
    	selectQueryTermList0.add(selectDataTerm0);
    	
    	List<String> selectColumnNameList0 = RequestState.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList0 = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair0 = new Pair<String, ColumnOrder>(selectColumnName0, ColumnOrder.ASC);
    	orderByList0.add(orderPair0);
    	
		//@SuppressWarnings("unused")
		List<RequestState> selectedRequestStateList = requestStateDao.select(selectColumnNameList0, selectQueryTermList0, orderByList0);
		
    	return selectedRequestStateList.get(0).getId();
    }

}
