package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CourseLoadAssociationDao;
import org.dselent.scheduling.server.dao.CourseLoadDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CourseLoad;
import org.dselent.scheduling.server.model.CourseLoadAssociation;
import org.dselent.scheduling.server.service.CourseLoadService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseLoadServiceImpl implements CourseLoadService {
	@Autowired
	private CourseLoadDao courseLoadDao;
	
	@Autowired
	private CourseLoadAssociationDao courseLoadAssociationDao;

	public CourseLoadServiceImpl() {
		//
	}

	@Override
	public List<CourseLoad> searchCourseLoad(int facultyId) throws SQLException {
		CourseLoadAssociation cla = courseLoadAssociationDao.findByFacultyId(facultyId);
		int courseLoadId = cla.getCourseLoadId();
		
		String selectColumnName = CourseLoad.getColumnName(CourseLoad.Columns.ID);
		//String selectUserName = "???????????";

		List<QueryTerm> selectQueryTermList = new ArrayList<>();

		QueryTerm selectCLATerm = new QueryTerm();
		selectCLATerm.setColumnName(selectColumnName);
		selectCLATerm.setComparisonOperator(ComparisonOperator.EQUAL);
		selectCLATerm.setValue(courseLoadId);
		selectQueryTermList.add(selectCLATerm);

		List<String> selectColumnNameList = CourseLoad.getColumnNameList();

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
		orderByList.add(orderPair1);

		@SuppressWarnings("unused")
		List<CourseLoad> selectedCourseLoadList = courseLoadDao.select(selectColumnNameList, selectQueryTermList, orderByList);
		
		return selectedCourseLoadList;
	}
	
	public Integer addCourseLoad(CourseLoad courseLoad) throws SQLException{
		courseLoad.setAmount(0);
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.AMOUNT));
		
		keyHolderColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.ID));
		keyHolderColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.TYPE));
		keyHolderColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.DELETED));
		keyHolderColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.CREATED_AT));
		
		return courseLoadDao.insert(courseLoad, insertColumnNameList, keyHolderColumnNameList);
		
	}
	
	public Integer addCourseLoadAssociation(int courseLoadId, int facultyId) throws SQLException{
		CourseLoadAssociation cla = new CourseLoadAssociation();
		cla.setCourseLoadId(courseLoadId);
		cla.setFacultyId(facultyId);
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.COURSE_LOAD_ID));
		insertColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID));
		
		keyHolderColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.ID));
		keyHolderColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.DELETED));
		keyHolderColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.CREATED_AT));
		
		return courseLoadAssociationDao.insert(cla, insertColumnNameList, keyHolderColumnNameList);
	}
	
	public Integer deleteCourseLoad(int facultyId) throws SQLException{
		String updateColumnName = CourseLoad.getColumnName(CourseLoad.Columns.DELETED);
		
		CourseLoadAssociation cla = courseLoadAssociationDao.findByFacultyId(facultyId);
		
		List<QueryTerm> updateQueryTermList = new ArrayList<>();
		QueryTerm updateDeletedTerm = new QueryTerm();
		
		updateDeletedTerm.setColumnName(CourseLoad.getColumnName(CourseLoad.Columns.ID));
		updateDeletedTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateDeletedTerm.setValue(cla.getCourseLoadId());
		updateQueryTermList.add(updateDeletedTerm);

		int result = courseLoadDao.update(updateColumnName, true, updateQueryTermList);
		return result;
	}
	
	public Integer deleteCourseLoadAssociation(int facultyId) throws SQLException{
		String updateColumnName = CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.DELETED);
		
		List<QueryTerm> updateQueryTermList = new ArrayList<>();
		QueryTerm updateDeletedTerm = new QueryTerm();
		
		updateDeletedTerm.setColumnName(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID));
		updateDeletedTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateDeletedTerm.setValue(facultyId);
		updateQueryTermList.add(updateDeletedTerm);

		int result = courseLoadAssociationDao.update(updateColumnName, true, updateQueryTermList);
		return result;
	}
	

}
