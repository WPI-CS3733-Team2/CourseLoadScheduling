package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.dto.ModifyCourseDto;
import org.dselent.scheduling.server.dto.SearchCourseDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.service.CourseService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.LogicalOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements CourseService
{
	@Autowired
	private CoursesDao coursesDao;
	
    public CourseServiceImpl()
    {
    	//
    }

    @Transactional
    @Override
	public List<Integer> createCourse(CreateCourseDto dto) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();
				
		Course course = new Course();
		course.setName(dto.getName());
		course.setNumber(dto.getNumber());
		course.setFrequency(Integer.parseInt(dto.getFrequency()));										//
    	
    	List<String> courseInsertColumnNameList = new ArrayList<>();
    	List<String> courseKeyHolderColumnNameList = new ArrayList<>();
    	
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NAME));		//
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NUMBER));
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.FREQUENCY));
    	
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.ID));
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.CREATED_AT));
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.UPDATED_AT));
		
    	rowsAffectedList.add(coursesDao.insert(course, courseInsertColumnNameList, courseKeyHolderColumnNameList));
		
		return rowsAffectedList;
	}
    
    @Transactional
    @Override
	public void modifyCourse(ModifyCourseDto dto) throws SQLException
	{
    	Course course = new Course();
		course.setName(dto.getName());
		course.setNumber(dto.getNumber());
		course.setFrequency(Integer.parseInt(dto.getFrequency()));
		
        Course courseToModify = coursesDao.findById(Integer.parseInt(dto.getId()));	
		
        List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Course.getColumnName(Course.Columns.NAME);
		QueryTerm idTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, courseToModify.getId(), null);
		queryTermList.add(idTerm);
		
		String updateColumnName;
		
		if(course.getName() != null) {
			updateColumnName = Course.getColumnName(Course.Columns.NAME);
			coursesDao.update(updateColumnName, course.getName(), queryTermList);
		}
		if(course.getNumber() != null) {
			updateColumnName = Course.getColumnName(Course.Columns.NUMBER);
			coursesDao.update(updateColumnName, course.getNumber(), queryTermList);
		}
		if(course.getFrequency() != null) {
			updateColumnName = Course.getColumnName(Course.Columns.FREQUENCY);
			coursesDao.update(updateColumnName, course.getFrequency(), queryTermList);
		}
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	@Override
	public List<Course> searchCourse(SearchCourseDto dto) throws SQLException {
		
    	Course course = new Course();
		course.setName(dto.getName());
		course.setNumber(dto.getNumber());
		course.setFrequency(Integer.parseInt(dto.getFrequency()));										//
    	
		List<String> courseColumnNameList = new ArrayList<>();
    	List<String> courseKeyHolderColumnNameList = new ArrayList<>();
    	
    	courseColumnNameList.add(Course.getColumnName(Course.Columns.NAME));		//
    	courseColumnNameList.add(Course.getColumnName(Course.Columns.NUMBER));
    	courseColumnNameList.add(Course.getColumnName(Course.Columns.FREQUENCY));
    	
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.ID));
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.CREATED_AT));
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.UPDATED_AT));
    	
    	List<QueryTerm> queryTermList = new ArrayList<>();
    	
    	if (course.getName() != null) {
			String queryColumnName = Course.getColumnName(Course.Columns.NAME);
			QueryTerm queryTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, course.getName(), LogicalOperator.OR);
			queryTerm.setLogicalOperator(null);
			queryTermList.add(queryTerm);
		}
    	if (course.getNumber() != null) {
			String queryColumnName = Course.getColumnName(Course.Columns.NUMBER);
			QueryTerm queryTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, course.getNumber(), LogicalOperator.OR);
			queryTerm.setLogicalOperator(null);
			queryTermList.add(queryTerm);
		}
    	if (course.getFrequency() != null) {
			String queryColumnName = Course.getColumnName(Course.Columns.FREQUENCY);
			QueryTerm queryTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, course.getFrequency(), LogicalOperator.OR);
			queryTerm.setLogicalOperator(null);
			queryTermList.add(queryTerm);
		}
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderBy = new Pair(Course.Columns.NUMBER, ColumnOrder.ASC);
    	orderByList.add(orderBy);
    	
    	List<Course> selectedCourses = new ArrayList<Course>();
    	selectedCourses = coursesDao.select(courseColumnNameList, queryTermList, orderByList);
    			
		return selectedCourses;
    }
    
}
