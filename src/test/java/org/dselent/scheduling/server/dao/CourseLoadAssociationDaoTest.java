package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.RequestDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.CourseLoadAssociation;
import org.dselent.scheduling.server.model.Request;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class CourseLoadAssociationDaoTest
{
	@Autowired
	private CourseLoadAssociationDao courseLoadAssociationDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void CourseLoadAssociationDao() throws SQLException
    {
    	/*
    	// INSERT
    	
    	CourseLoadAssociation cla1 = new CourseLoadAssociation();
    	cla1.setFacultyId(1);
    	cla1.setCourseLoadId(3);
    	cla1.setDeleted(false);
    	
    	List<String> insertColumnNameList = new ArrayList<>();
    	List<String> keyHolderColumnNameList = new ArrayList<>();
    	
    	insertColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID));
    	insertColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.COURSE_LOAD_ID));
    	insertColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.DELETED));

    	keyHolderColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.ID));
    	keyHolderColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.CREATED_AT));
   	
    	courseLoadAssociationDao.insert(cla1, insertColumnNameList, keyHolderColumnNameList);
    	*/

    	// UPDATE
    	
    	String updateColumnName = CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID);
    	Integer oldFacultyId = 3;
    	Integer newFacultyId = 50;
    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
    	QueryTerm updateFacultyIdTerm = new QueryTerm();
    	updateFacultyIdTerm.setColumnName(updateColumnName);
    	updateFacultyIdTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateFacultyIdTerm.setValue(oldFacultyId);
    	updateQueryTermList.add(updateFacultyIdTerm);
    	
    	courseLoadAssociationDao.update(updateColumnName, newFacultyId, updateQueryTermList);
    	
    	/*
    	// SELECT
    	// by faculty_id
//    	
    	String selectColumnName = CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID);
    	Integer selectData = 2;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectDataTerm = new QueryTerm();
    	selectDataTerm.setColumnName(selectColumnName);
    	selectDataTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectDataTerm.setValue(selectData);
    	selectQueryTermList.add(selectDataTerm);
    	
    	List<String> selectColumnNameList = CourseLoadAssociation.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		@SuppressWarnings("unused")
		List<CourseLoadAssociation> selectedCourseLoadAssociationList = courseLoadAssociationDao.select(selectColumnNameList, selectQueryTermList, orderByList);
//    	

 */
    	System.out.println();

    }
}
